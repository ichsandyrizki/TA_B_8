package tugaskelompokb8.apap.situ.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.service.JenisSuratService;
import tugaskelompokb8.apap.situ.service.PengajuanSuratService;
import tugaskelompokb8.apap.situ.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PengajuanSuratController {

    @Autowired
    private PengajuanSuratService pengajuanSuratService;

    @Autowired
    private JenisSuratService jenisSuratService;

    @Autowired
    private UserService userService;

    @Autowired
    UserDb userDb;

    @RequestMapping(value = "/pengajuanSurat/add", method = RequestMethod.GET)
    public String addPengajuanSuratFormPage(Model model) {
        PengajuanSuratModel suratBaru = new PengajuanSuratModel();
        List<JenisSuratModel> listJenisSurat = jenisSuratService.getJenisSuratList();
        List<PengajuanSuratModel> pengajuanSuratModelList = pengajuanSuratService.getPengajuanSuratList();
        model.addAttribute("pengajuan_list",pengajuanSuratModelList);
        model.addAttribute("surat", suratBaru);
        model.addAttribute("listJenisSurat", listJenisSurat);
        return "form-pengajuan-surat";
    }

    @RequestMapping(value = "/pengajuanSurat/add", method = RequestMethod.POST, params={"submit"})
    private String addPengajuanSuratSubmit(@ModelAttribute PengajuanSuratModel surat, Model model){
        surat.setTanggalDisetujui(null);
        surat.setStatus(0);
        surat.setNomorSurat("0");
        surat.setUser(userService.getUserCurrentLoggedIn());
        surat.setTanggalPengajuan(Date.valueOf(LocalDate.now()));
        List<JenisSuratModel> listJenisSurat = jenisSuratService.getJenisSuratList();
        List<PengajuanSuratModel> pengajuanSuratModelList = pengajuanSuratService.getPengajuanSuratList();
        pengajuanSuratService.addPengajuanSurat(surat);
        model.addAttribute("surat", surat);
        model.addAttribute("listJenisSurat", listJenisSurat);
        model.addAttribute("pengajuan_list", pengajuanSuratModelList);
        return "form-pengajuan-surat";
    }

    @RequestMapping(value = "/pengajuanSurat/delete/{idSurat}")
    public String deleteStore(
            @PathVariable(value="idSurat") Long idSurat, @ModelAttribute PengajuanSuratModel surat,
            Model model
    ){
        pengajuanSuratService.deletePengajuanSurat(idSurat);
        List<JenisSuratModel> listJenisSurat = jenisSuratService.getJenisSuratList();
        List<PengajuanSuratModel> pengajuanSuratModelList = pengajuanSuratService.getPengajuanSuratList();
        model.addAttribute("pengajuan_list",pengajuanSuratModelList);
        model.addAttribute("listJenisSurat", listJenisSurat);
        model.addAttribute("surat",surat);

        return "form-pengajuan-surat";
    }

    @RequestMapping("/pengajuanSurat/statuses")
    public String pengajuanViewAll(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }else {
            username = principal.toString();
        }
        if (userDb.findByUsername(username).getRole().getIdRole() == 1){
            model.addAttribute("pengajuan_list", pengajuanSuratService.getPengajuanSuratList());
        }
        if (userDb.findByUsername(username).getRole().getIdRole() == 2){
            model.addAttribute("pengajuan_list", pengajuanSuratService.getPengajuanSuratList());
        }
        if (userDb.findByUsername(username).getRole().getIdRole() == 3){
            model.addAttribute("pengajuan_list", pengajuanSuratService.findPengajuanByUser(userDb.findByUsername(username)));
        }
        if (userDb.findByUsername(username).getRole().getIdRole() == 4){
            model.addAttribute("pengajuan_list", pengajuanSuratService.findPengajuanByUser(userDb.findByUsername(username)));
        }

        return "pengajuan-view-all";
    }


    @RequestMapping(value = "/pengajuanSurat/update/{idPengajuanSurat}",method = RequestMethod.GET)
    public String updatePengajuanSuratFormPage(
            Model model,
//            HttpServletRequest request, HttpSession session,
            @PathVariable(value="idPengajuanSurat") Long idPengajuanSurat
    ){
        PengajuanSuratModel pengajuanSuratModel = pengajuanSuratService.getPengajuanById(idPengajuanSurat).get();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }else {
            username = principal.toString();
        }

        if (userDb.findByUsername(username).getRole().getIdRole() == 1){
            if (pengajuanSuratModel.getStatus() == 0){
                model.addAttribute("pengajuanSurat", pengajuanSuratModel);
                return "pengajuan-update-mk2";
            }else{
                return "404";
            }
        }
        if (userDb.findByUsername(username).getRole().getIdRole() == 2){
            if (pengajuanSuratModel.getStatus() == 2){
                model.addAttribute("pengajuanSurat", pengajuanSuratModel);
                return "pengajuan-update-mk2";
            }else{
                return "404";
            }

        }
        model.addAttribute("pengajuanSurat", pengajuanSuratModel);
        return "pengajuan-update-mk2";
    }

    @RequestMapping(value = "/pengajuanSurat/update/{idPengajuanSurat}",method = RequestMethod.POST)
    public String updatePengajuanSuratSubmit(
            Model model,
            @ModelAttribute PengajuanSuratModel pengajuanSuratModel,
            @PathVariable(value="idPengajuanSurat") Long idPengajuanSurat
    ){
        PengajuanSuratModel updatedModel = pengajuanSuratService.updatePengajuan(pengajuanSuratModel);
        String message = "pengajuan berhasil diubah";
        model.addAttribute("message", message);
        return "success-page";
    }

}
