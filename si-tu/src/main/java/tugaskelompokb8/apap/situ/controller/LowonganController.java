package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugaskelompokb8.apap.situ.model.JenisLowonganModel;
import tugaskelompokb8.apap.situ.model.LowonganModel;
import tugaskelompokb8.apap.situ.rest.UserPerpusDetail;
import tugaskelompokb8.apap.situ.service.JenisLowonganService;
import tugaskelompokb8.apap.situ.service.LowonganService;
import tugaskelompokb8.apap.situ.service.UserService;

import java.text.ParseException;
import java.util.List;

@Controller
public class LowonganController {
    @Autowired
    JenisLowonganService jenisLowonganService;

    @Autowired
    LowonganService lowonganService;

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
    private String addPengajuanSuratSubmit(@ModelAttribute LowonganModel lowongan, Model model){
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
}
