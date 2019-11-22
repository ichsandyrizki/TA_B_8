package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tugaskelompokb8.apap.situ.model.LowonganModel;
import tugaskelompokb8.apap.situ.model.PasswordModel;
import tugaskelompokb8.apap.situ.repository.PengajuanSuratDb;
import tugaskelompokb8.apap.situ.repository.RoleDb;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.service.LowonganService;
import tugaskelompokb8.apap.situ.service.LowonganServiceImpl;
import tugaskelompokb8.apap.situ.service.PengajuanSuratService;
import tugaskelompokb8.apap.situ.service.UserService;

@Controller
public class PageController {
    @Qualifier("userServiceImpl")

	@Autowired
    UserService userService;

    @Autowired
    LowonganService lowonganService;

    @Autowired
    PengajuanSuratService pengajuanSuratService;
	
    @RequestMapping("/")
    private String home(Model model){
//        if(userService.getUserCurrentLoggedIn().getRole().getNama().equals("Admin TU")){
//            model.addAttribute("isAdmin", true);
//        }else{
//            model.addAttribute("isAdmin", false);
//        }
        model.addAttribute("jmlUser", userService.getListUser().size());
        model.addAttribute("jmlSurat", pengajuanSuratService.getPengajuanSuratList().size());
        model.addAttribute("jmlLowongan",lowonganService.getLowonganList().size());

        return "index";
    }
    
    @RequestMapping("/login")
    private String login() {
    	return "login";
    }
    
    @RequestMapping("/changePassword")
	public String changePassword(Model model) {
		PasswordModel changePassword = new PasswordModel();
		model.addAttribute("changePass", changePassword);
		model.addAttribute("message", "");
		return "change-password";
	}
}

