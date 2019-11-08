package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.service.JenisSuratService;
import tugaskelompokb8.apap.situ.service.PengajuanSuratService;
import tugaskelompokb8.apap.situ.service.UserService;

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

    @RequestMapping(value = "/surat/add", method = RequestMethod.GET)
    public String addPengajuanSuratFormPage(Model model) {
        PengajuanSuratModel suratBaru = new PengajuanSuratModel();
        List<JenisSuratModel> listJenisSurat = jenisSuratService.getJenisSuratList();
        model.addAttribute("surat", suratBaru);
        model.addAttribute("listJenisSurat", listJenisSurat);
        return "form-pengajuan-surat";
    }

    @RequestMapping(value = "/surat/add", method = RequestMethod.POST, params={"submit"})
    private String addPengajuanSuratSubmit(@ModelAttribute PengajuanSuratModel surat){
        surat.setTanggalDisetujui(null);
        surat.setStatus(0);
        surat.setNomorSurat("0");
        surat.setUser(userService.getUserCurrentLoggedIn());
        surat.setTanggalPengajuan(Date.valueOf(LocalDate.now()));
        pengajuanSuratService.addPengajuanSurat(surat);
        return "index";
    }

    @RequestMapping(value = "/pengajuanSurat/delete/{idSurat}")
    public String deleteStore(
            @PathVariable(value="idSurat") Long idSurat,
            Model model
    ){
        pengajuanSuratService.deletePengajuanSurat(idSurat);

        return "index";
    }

    @RequestMapping("/pengajuanSurat/statuses")
    public String pengajuanViewAll(Model model){
        List<PengajuanSuratModel> pengajuanSuratModelList = pengajuanSuratService.getPengajuanSuratList();

        model.addAttribute("pengajuan_list",pengajuanSuratModelList);

        return "pengajuan-view-all";
    }


    @RequestMapping(value = "/pengajuanSurat/update/{idPengajuanSurat}",method = RequestMethod.GET)
    public String updatePengajuanSuratFormPage(
            Model model,
            @PathVariable(value="idPengajuanSurat") Long idPengajuanSurat
    ){
        PengajuanSuratModel pengajuanSuratModel = pengajuanSuratService.getPengajuanById(idPengajuanSurat).get();

        model.addAttribute("pengajuanSurat", pengajuanSuratModel);

        return "pengajuan-update";
    }

    @RequestMapping(value = "/pengajuanSurat/update/{idPengajuanSurat}",method = RequestMethod.POST)
    public String updatePengajuanSuratSubmit(
            Model model,
            @ModelAttribute PengajuanSuratModel pengajuanSuratModel,
            @PathVariable(value="idPengajuanSurat") Long idPengajuanSurat
    ){
        PengajuanSuratModel updatedModel = pengajuanSuratService.updatePengajuan(pengajuanSuratModel);

        return "redirect:/pengajuanSurat/statuses";
    }

}
