package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.PasswordModel;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.rest.BaseResponse;
import tugaskelompokb8.apap.situ.rest.UserSivitasModel;
import tugaskelompokb8.apap.situ.service.UserDetailsServiceImpl;
import tugaskelompokb8.apap.situ.service.UserRestService;
import tugaskelompokb8.apap.situ.service.UserService;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	UserDb userDb;

	@Autowired
	UserRestService userRestService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserModel user) {
		userService.addUser(user);
		return "index";

	}

	@RequestMapping(value= "/changePassword", method = RequestMethod.POST)
	public String changePassSubmit(@ModelAttribute PasswordModel changePassword,
			Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		UserModel user = userDb.findByUsername(currentPrincipalName);
		
		PasswordEncoder token = new BCryptPasswordEncoder();
		
		System.out.println(user.getUsername());
		
		if(!token.matches(changePassword.getOldPassword(), user.getPassword())){
			model.addAttribute("message", "Invalid Old Password");
			PasswordModel changePassword2 = new PasswordModel();
			model.addAttribute("changePass", changePassword2);
			return "change-password";
			
		}if(!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())){
			model.addAttribute("message","Password Doesnt Match");
			PasswordModel changePassword3 = new PasswordModel();
			model.addAttribute("changePass", changePassword3);
			return "change-password";
		
		}else {
			userService.changeUser(user, changePassword.getNewPassword());
			model.addAttribute("messages","");
			return "index";
		}
	}
	
	//WEBSERVICE GET USER PROFILE DARI SIVITAS
    @RequestMapping("/view")
	public String getUserProfile(Model model) throws ParseException {
	    UserModel user = userService.getUserCurrentLoggedIn();
	    System.out.println(user.getRole().getIdRole() +"  " +user.getIdUser());
	    if (user.getRole().getIdRole() < 5){
			System.out.println("masuk ke if < 5");
			UserSivitasModel userModel = new UserSivitasModel();
			Mono<BaseResponse> respon = userRestService.getUserData(user.getIdUser(), user.getRole().getIdRole());
			LinkedHashMap<String ,String> data = (LinkedHashMap<String , String>) Objects.requireNonNull(respon.block()).getResult();
			String tanggal = data.get("tanggalLahir");
			userModel.setNama(data.get("nama"));
			userModel.setAlamat(data.get("alamat"));
			if(user.getRole().getIdRole() == 4){
				userModel.setNi("nis");
			}else if(user.getRole().getIdRole() == 2){
				userModel.setNi("nip");
			} else {
				userModel.setNi("nig");
			}
			userModel.setTanggalLahir(new SimpleDateFormat("yyyy-MM-dd").parse(tanggal));
			userModel.setTempatLahir(data.get("tempatLahir"));
			userModel.setTelepon(data.get("telepon"));
			model.addAttribute("user", userModel);
	    } else {
			System.out.println("masuk ke if > 4");
	    	model.addAttribute("user", user);
		}
		return "profile";
	}
}
