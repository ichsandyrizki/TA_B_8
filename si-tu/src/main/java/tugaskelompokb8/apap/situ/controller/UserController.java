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

import tugaskelompokb8.apap.situ.model.PasswordModel;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.service.UserDetailsServiceImpl;
import tugaskelompokb8.apap.situ.service.UserRestService;
import tugaskelompokb8.apap.situ.service.UserService;

import java.math.BigInteger;

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
    @RequestMapping("/view") //Ini nantinya gimana cara dapetin si idUser encrypted nya ya? yg nge pass siapa, cara nya gimana jir?
	public String getUserProfile(@PathVariable Model model) {
	    String idUser = userService.getUserCurrentLoggedIn().getIdUser();

	    if(!idUser.isEmpty()){
            long roleUser = userService.getUserCurrentLoggedIn().getRole().getIdRole();
            Object userData = userRestService.getUser(idUser,roleUser);
            model.addAttribute("user", userData);
        }else{

        }

		return "profile";
	}
}
