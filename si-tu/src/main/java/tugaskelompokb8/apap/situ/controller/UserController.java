package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.PasswordModel;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.RoleDb;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.rest.BaseResponse;
import tugaskelompokb8.apap.situ.rest.BaseRest;
import tugaskelompokb8.apap.situ.rest.UserSivitasModel;
import tugaskelompokb8.apap.situ.service.UserDetailsServiceImpl;
import tugaskelompokb8.apap.situ.service.UserRestService;
import tugaskelompokb8.apap.situ.service.UserService;

import javax.validation.Valid;
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
    RoleDb roleDb;

    @Autowired
    private UserRestService userRestService;


	@RequestMapping("/addUser")
	private String addUser(@RequestParam(value = "userIsExist", required=false) boolean cassieyah, Model model) {
		UserSivitasModel userSivitasModel = new UserSivitasModel();
		if(userService.getUserCurrentLoggedIn().getRole().equals("Admin TU")){
			model.addAttribute("isAdmin", true);
		}else{
			model.addAttribute("isAdmin", false);
		}
		model.addAttribute("user", userSivitasModel);
		model.addAttribute("listRole", roleDb.findAll());
		model.addAttribute("userIsExist", cassieyah);
		return "form-add-user";
	}

	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute @Valid UserSivitasModel userSivitas,
								 BindingResult result,
								 WebRequest req,
								 Error error,
								 RedirectAttributes redirAttr) {

		boolean userIsExist = false;
		for(int i = 0; i < userService.getListUser().size(); i++){
			if(userService.getListUser().get(i).getUsername().equals(userSivitas.getUsername())){
				userIsExist = true;
				break;
			}
		}

		if(userIsExist == false){
			UserModel user = userService.addUser(userSivitas);
			userSivitas.setIdUSer(user.getIdUser());
			Mono<BaseRest> api = null;
			if(roleDb.findByIdRole(userSivitas.getIdRole()).getNama().equals("Admin TU")||
					roleDb.findByIdRole(userSivitas.getIdRole()).getNama().equals("Kepala Sekolah")||
					roleDb.findByIdRole(userSivitas.getIdRole()).getNama().equals("Guru")||
					roleDb.findByIdRole(userSivitas.getIdRole()).getNama().equals("Siswa")){
				api = userRestService.registerUser(userSivitas);
				if(Objects.requireNonNull(api.block()).getStatus() == 200){
				} else{
					userService.deleteUser(user);
				}
			}
		}

		redirAttr.addAttribute("userIsExist", userIsExist);

		return "redirect:/user/addUser";
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
        public String getUserProfile (Model model) throws ParseException {
            UserModel user = userService.getUserCurrentLoggedIn();
            model.addAttribute("jmlLowongan", user.getListLowongan().size());
            model.addAttribute("jmlSurat", user.getListPengajuanSurat().size());
            model.addAttribute("role" ,user.getRole());
            model.addAttribute("username", user.getUsername());

            if (user.getRole().getIdRole() < 5) {

                UserSivitasModel userModel = new UserSivitasModel();
                Mono<BaseResponse> respon = userRestService.getUserData(user.getIdUser(), user.getRole().getIdRole());
                LinkedHashMap<String, String> data = (LinkedHashMap<String, String>) Objects.requireNonNull(respon.block()).getResult();

                userModel.setIdRole(user.getRole().getIdRole());
                userModel.setUsername(user.getUsername());
                userModel.setNama(data.get("nama"));
                userModel.setAlamat(data.get("alamat"));
                if (user.getRole().getIdRole() == 4) {
                    userModel.setNi(data.get("nis"));
                } else if (user.getRole().getIdRole() == 2) {
                    userModel.setNi(data.get("nip"));
                } else {
                    userModel.setNi(data.get("nig"));
                }
                String tanggal = data.get("tanggalLahir");
                userModel.setTanggalLahir(new SimpleDateFormat("yyyy-MM-dd").parse(tanggal));
                userModel.setTempatLahir(data.get("tempatLahir"));
                userModel.setTelepon(data.get("telepon"));
                model.addAttribute("user", userModel);
            }
            return "profile";
        }

    }

