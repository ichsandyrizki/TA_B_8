package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.service.JenisSuratService;

import java.util.List;

@Controller
public class JenisSuratController {
    @Qualifier("jenisSuratServiceImpl")
    @Autowired
    private JenisSuratService jenisSuratService;

    @RequestMapping(value = "/jenisSurat/add", method = RequestMethod.GET)
    public String addJenisSuratFormPage(Model model){
        JenisSuratModel newJenisSurat = new JenisSuratModel();
        List<JenisSuratModel> jenisSuratList = jenisSuratService.getJenisSuratList();

        model.addAttribute("notthide", false);
        model.addAttribute("jenisSurat", newJenisSurat);
        model.addAttribute("tilepage", "Form Add Jenis Surat");
        model.addAttribute("jenisSuratList", jenisSuratList);

        return "form-add-jenis-surat";
    }

    @RequestMapping(value = "/jenisSurat/add", method = RequestMethod.POST)
    public String addJenisSuratSubmit(@ModelAttribute JenisSuratModel jenisSurat, Model model){
        boolean success = jenisSuratService.addJenisSurat(jenisSurat);
        List<JenisSuratModel> jenisSuratList = jenisSuratService.getJenisSuratList();

        if(success==true){
            model.addAttribute("success", "Jenis Surat sudah terdaftar");
        }
        model.addAttribute("nothide",success);
        model.addAttribute("jenisSuratList", jenisSuratList);
        model.addAttribute("nama", jenisSurat.getNama());
        model.addAttribute("titlepage","Add Jenis Surat");
        return "form-add-jenis-surat";
    }

    @RequestMapping(value = "jenisSurat/delete/{idJenisSurat}", method = RequestMethod.GET)
    public String deleteJenisSurat(@PathVariable(value = "idJenisSurat") long idJenisSurat, Model model){
        jenisSuratService.deleteJenisSurat(idJenisSurat);
        model.addAttribute("nothide", false);
        model.addAttribute("titlepage", "Form Add Jenis Surat");
        model.addAttribute("jenisSuratList",jenisSuratService.getJenisSuratList());
        return "form-add-jenis-surat";
    }

    /*@RequestMapping(value = "/jenisSurat/add", method = RequestMethod.GET)
    public String addJenisSuratFormPage(Model model){
        JenisSuratModel newJenisSurat = new JenisSuratModel();
        model.addAttribute("jenisSurat", newJenisSurat);
        return "formAdd-jenis-surat";
    }*/

/*    //TODO: Form Add Jenis Obat
    @RequestMapping(value = "/jenis-lowongan/add", method = RequestMethod.GET)
    public String addJenisLowonganFromHomePage(Model model) {
        JenisLowonganModel newJenisLowongan = new JenisLowonganModel();
        List<JenisLowonganModel> jenisList = jenisLowonganService.getJenisList();

        model.addAttribute("nothide", false);
        model.addAttribute("jenisLowongan", newJenisLowongan);
        model.addAttribute("titlepage", "Form Add Jenis Lowongan");
        model.addAttribute("jenisList", jenisList);
        return "form-add-jenis-lowongan";
    }*/

    /*@RequestMapping(value = "/jenisSurat/add", method = RequestMethod.POST)
    public String addJenisSuratSubmit(@ModelAttribute JenisSuratModel jenisSurat, Model model){
        System.out.println(jenisSuratService.getJenisSuratByNama(jenisSurat.getNama()));
        JenisSuratModel jenis = jenisSuratService.getJenisSuratByNama(jenisSurat.getNama());
        if(jenis != null){
            model.addAttribute("nama", jenisSurat.getNama());
            model.addAttribute("message","Sudah terdapat didalam sistem");
            return "failPage-jenis-surat";
        }else{
            jenisSuratService.addJenisSurat(jenisSurat);
            model.addAttribute("nama", jenisSurat.getNama());
            model.addAttribute("message","Berhasil menambahkan jenis surat");
            return "successPage-jenis-surat";
        }
    }*/

    /*//API yang digunakan untuk submit form yang telah anda masukan di halaman add gudang
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
    }*/

/*    @RequestMapping(value = "/jenisSurat/view")
    public String viewAll(Model model){
        //Mengambil data dari jenis surat yang ada
        List<JenisSuratModel> jenisSuratList = jenisSuratService.getJenisSuratList();
        model.addAttribute("jenisSuratList",jenisSuratList);
        return "viewAll-jenis-surat";
    }*/


    /*@RequestMapping(value = "jenisSurat/delete/{idJenisSurat}", method = RequestMethod.GET)
    public String deleteStore(@PathVariable Long idJenisSurat, Model model){
        if(jenisSuratService.getJenisSuratById(idJenisSurat) == null){
            model.addAttribute("message","Jenis surat tidak ditemukan");
            return "failPage-jenis-surat";
        }else{
            String nama = jenisSuratService.getJenisSuratById(idJenisSurat).getNama();
            jenisSuratService.deleteJenisSurat(idJenisSurat);
            model.addAttribute("message",",Jenis surat berhasil dihapus");
            model.addAttribute("nama", " ");
            return "successPage-jenis-surat";
        }
    }*/

    /*//TODO:Menghapus Jenis Lowongan
    @RequestMapping(value = "/jenis-lowongan/delete/{idJenis}", method = RequestMethod.GET)
    public String deleteJenisLowongan(@PathVariable(value = "idJenis") long idJenis, Model model) {
        jenisLowonganService.deleteObat(jenisLowonganService.getJenisById(idJenis));
        model.addAttribute("nothide", false);
        model.addAttribute("titlepage", "Form Add Jenis Lowongan");
        model.addAttribute("jenisList", jenisLowonganService.getJenisList());
        return "form-add-jenis-lowongan";
    }*/
}
