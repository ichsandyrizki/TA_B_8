package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugaskelompokb8.apap.situ.model.JenisLowonganModel;
import tugaskelompokb8.apap.situ.model.LowonganModel;

import tugaskelompokb8.apap.situ.rest.UserPerpusDetail;
import tugaskelompokb8.apap.situ.repository.LowonganDb;

import tugaskelompokb8.apap.situ.service.JenisLowonganService;
import tugaskelompokb8.apap.situ.service.LowonganService;
import tugaskelompokb8.apap.situ.service.UserService;

import java.text.ParseException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class LowonganController {
    @Autowired
    JenisLowonganService jenisLowonganService;

    @Autowired
    LowonganService lowonganService;
    
    @Autowired
    LowonganDb lowonganDb;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/lowongan/add", method = RequestMethod.GET)
    public String addLowonganFormPage(Model model) {
        LowonganModel lowonganBaru = new LowonganModel();
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getJenisList();
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();
        model.addAttribute("listJenisLowongan",listJenisLowongan);
        model.addAttribute("listLowongan", listLowongan);
        model.addAttribute("lowongan", lowonganBaru);
        return "form-add-lowongan";
    }

    @RequestMapping(value = "/lowongan/add", method = RequestMethod.POST)
    private String addLowonganSubmit(@ModelAttribute LowonganModel lowongan, Model model){
        lowongan.setUser(userService.getUserCurrentLoggedIn());
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();
        model.addAttribute("listLowongan", listLowongan);
        model.addAttribute("lowongan", lowongan);
        lowonganService.addLowongan(lowongan);
        return "form-add-lowongan";
    }

//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public String getUsers(Model model) throws ParseException {
////        UserPerpusDetail user = new UserPerpusDetail();
//////        UserModel userModel = userService.findByUsername(principal.getName());
////        Mono<Base> respon = userRestService.profil(userModel.getUuid());
////        LinkedHashMap<String, String> data = (LinkedHashMap<String, String>) Objects.requireNonNull(respon.block()).getResult();
////        String tanggal = data.get("tanggalLahir");
////        userDTO.setNama(data.get("nama"));
////        userDTO.setAlamat(data.get("alamat"));
////        userDTO.setNip(data.get("nip"));
////        userDTO.setTanggalLahir(new SimpleDateFormat("yyyy-MM-dd").parse(tanggal));
////        userDTO.setTempatLahir(data.get("tempatLahir"));
////        userDTO.setTelepon(data.get("telepon"));
////
////        model.addAttribute("user", userDTO);
////        return "user/profile";
//
//    }

    
    @RequestMapping(value="lowongan/update/{idLowongan}", method = RequestMethod.GET)
    public String changeLowonganFormPage(@PathVariable(value="idLowongan") Long idLowongan, Model model) {
    	LowonganModel lowongan = lowonganDb.findByIdLowongan(idLowongan);
    	Date openDate = lowongan.getTanggalDibuka();
    	Date currDate = Date.valueOf(LocalDate.now());
    	List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getJenisList();
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();
    	
        if(userService.getUserCurrentLoggedIn() != lowongan.getUser()) {
    		model.addAttribute("message", "user tidak dapat mengubah lowongan");
    		return "lowongan-opened";
    	}
        if(currDate.compareTo(openDate) > 0 || currDate.compareTo(openDate) == 0) {
    		model.addAttribute("message", "Lowongan telah dibuka");
    		return "lowongan-opened";
    	}
    	model.addAttribute("lowongan", lowongan);
    	model.addAttribute("listJenisLowongan",listJenisLowongan);
        model.addAttribute("listLowongan", listLowongan);
    	return "change-lowongan";
    }
    
    @RequestMapping(value="lowongan/update/{idLowongan}", method = RequestMethod.POST)
    public String changeLowonganSubmit(@PathVariable(value="idLowongan") Long idLowongan,
    		@ModelAttribute LowonganModel lowongan, Model model) {
    	lowongan.setUser(userService.getUserCurrentLoggedIn());
    	lowonganService.changeLowongan(lowongan, idLowongan);
    	
    	List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getJenisList();
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();
        LowonganModel newLowongan = new LowonganModel();
        model.addAttribute("listJenisLowongan",listJenisLowongan);
        model.addAttribute("listLowongan", listLowongan);
        model.addAttribute("lowongan", newLowongan);
    	
        return "form-add-lowongan";
    }
}
