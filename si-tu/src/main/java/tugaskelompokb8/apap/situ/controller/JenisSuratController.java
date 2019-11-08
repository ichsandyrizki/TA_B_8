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
        model.addAttribute("jenisSurat", newJenisSurat);
        return "formAdd-jenis-surat";
    }
    @RequestMapping(value = "/jenisSurat/add", method = RequestMethod.POST)
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
    }
    @RequestMapping(value = "jenisSurat/delete/{idJenisSurat}", method = RequestMethod.GET)
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
    }
    @RequestMapping(value = "/jenisSurat/view")
    public String viewAll(Model model){
            //Mengambil data dari jenis surat yang ada
            List<JenisSuratModel> jenisSuratList = jenisSuratService.getJenisSuratList();
            model.addAttribute("jenisSuratList",jenisSuratList);
            return "viewAll-jenis-surat";
    }
}
