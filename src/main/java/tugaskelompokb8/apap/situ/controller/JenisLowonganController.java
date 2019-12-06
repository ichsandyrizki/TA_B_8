package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugaskelompokb8.apap.situ.model.JenisLowonganModel;
import tugaskelompokb8.apap.situ.service.JenisLowonganService;

import java.util.List;

@Controller
public class JenisLowonganController {
    @Qualifier("jenisLowonganServiceImpl")

    @Autowired
    private JenisLowonganService jenisLowonganService;

    //TODO: Form Add Jenis Obat
    @RequestMapping(value = "/jenis-lowongan/add", method = RequestMethod.GET)
    public String addJenisLowonganFromHomePage(Model model) {
        JenisLowonganModel newJenisLowongan = new JenisLowonganModel();
        List<JenisLowonganModel> jenisList = jenisLowonganService.getJenisList();

        model.addAttribute("nothide", false);
        model.addAttribute("jenisLowongan", newJenisLowongan);
        model.addAttribute("titlepage", "Form Add Jenis Lowongan");
        model.addAttribute("jenisList", jenisList);
        return "form-add-jenis-lowongan";
    }

    //API yang digunakan untuk submit form yang telah anda masukan di halaman add gudang
    @RequestMapping(value = "/jenis-lowongan/add", method = RequestMethod.POST)
    private String addLowonganSubmit(@ModelAttribute JenisLowonganModel jenisLowongan, Model model){
        boolean success = jenisLowonganService.addJenisLowongan(jenisLowongan);
        List<JenisLowonganModel> jenisList = jenisLowonganService.getJenisList();

        if(success==true){
            model.addAttribute("success", "Lowongan sudah terdaftar");
        }

        model.addAttribute("nothide", success);
        model.addAttribute("jenisList", jenisList);
        model.addAttribute("nama", jenisLowongan.getNama());
        model.addAttribute("titlepage", "Add Jenis Lowongan");
        return "form-add-jenis-lowongan";
    }

    //TODO:Menghapus Jenis Lowongan
    @RequestMapping(value = "/jenis-lowongan/delete/{idJenis}", method = RequestMethod.GET)
    public String deleteJenisLowongan(@PathVariable(value = "idJenis") long idJenis, Model model) {
        jenisLowonganService.deleteJenisLowongan(jenisLowonganService.getJenisById(idJenis));
        model.addAttribute("nothide", false);
        model.addAttribute("titlepage", "Form Add Jenis Lowongan");
        model.addAttribute("jenisList", jenisLowonganService.getJenisList());
        return "form-add-jenis-lowongan";
    }

}
