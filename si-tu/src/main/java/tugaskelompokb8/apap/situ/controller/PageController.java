package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tugaskelompokb8.apap.situ.model.PasswordModel;
import tugaskelompokb8.apap.situ.repository.RoleDb;

@Controller
public class PageController {

	@Autowired
	RoleDb roleDb;
	
    @RequestMapping("/")
    private String home(){
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

