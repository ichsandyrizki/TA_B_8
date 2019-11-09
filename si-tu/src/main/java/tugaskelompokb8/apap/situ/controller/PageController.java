package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tugaskelompokb8.apap.situ.model.LowonganModel;
import tugaskelompokb8.apap.situ.model.PasswordModel;
import tugaskelompokb8.apap.situ.repository.PengajuanSuratDb;
import tugaskelompokb8.apap.situ.repository.RoleDb;
import tugaskelompokb8.apap.situ.repository.UserDb;

@Controller
public class PageController {

	@Autowired
	RoleDb roleDb;

	@Autowired
    UserDb userDb;

	@Autowired
    PengajuanSuratDb pengajuanSuratDb;
	
    @RequestMapping("/")
    private String home(Model model){
        model.addAttribute("jmlUser", userDb.findAll().size());
        model.addAttribute("jmlSurat", pengajuanSuratDb.findAll().size());
        model.addAttribute("jmlLowongan",0);
        return "index";
    }
    
    @RequestMapping("/addUser")
    private String addUser(Model model) {
    	model.addAttribute("listRole", roleDb.findAll());
    	return "add-user";
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

