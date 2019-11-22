package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.PasswordModel;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.RoleDb;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.rest.BaseRest;
import tugaskelompokb8.apap.situ.rest.UserSivitasModel;
import tugaskelompokb8.apap.situ.service.UserRestService;
import tugaskelompokb8.apap.situ.service.UserService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	UserDb userDb;

	@Autowired
	RoleDb roleDb;

	@Autowired
	private UserRestService userRestService;

	@RequestMapping("/addUser")
	private String addUser(Model model) {
		UserSivitasModel userSivitasModel = new UserSivitasModel();
		if(userService.getUserCurrentLoggedIn().getRole().equals("Admin TU")){
			model.addAttribute("isAdmin", true);
		}else{
			model.addAttribute("isAdmin", false);
		}
		model.addAttribute("user", userSivitasModel);
		model.addAttribute("listRole", roleDb.findAll());
		return "form-add-user";
	}

	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute @Valid UserSivitasModel userSivitas,
								 BindingResult result,
								 WebRequest req,
								 Error error) {
		UserModel user = userService.addUser(userSivitas);
		userSivitas.setIdUSer(user.getIdUser());

		Mono<BaseRest> api = null;
		if(roleDb.findByIdRole(userSivitas.getIdRole()).getNama().equals("Admin Tu")||
				roleDb.findByIdRole(userSivitas.getIdRole()).getNama().equals("Kepala Sekolah")||
				roleDb.findByIdRole(userSivitas.getIdRole()).getNama().equals("Guru")||
				roleDb.findByIdRole(userSivitas.getIdRole()).getNama().equals("Siswa")){
			api = userRestService.registerUser(userSivitas);
			if(Objects.requireNonNull(api.block()).getStatus() == 200){
				return "redirect:/";
			} else{
				userService.deleteUser(user);
				return "redirect:/user/addUser";
			}
		}
		return "redirect:/";
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

}
