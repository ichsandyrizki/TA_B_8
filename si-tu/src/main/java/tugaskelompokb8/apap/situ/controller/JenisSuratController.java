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

@Controller
public class JenisSuratController {
    @Qualifier("jenisSuratServiceImpl")
    @Autowired
    private JenisSuratService jenisSuratService;


    @RequestMapping(value = "jenisSurat/add", method = RequestMethod.GET)
    public String addJenisSuratFormPage(Model model){
        JenisSuratModel newJenisSurat = new JenisSuratModel();
        model.addAttribute("jenisSurat", newJenisSurat);
        return "form-add-jenis-surat";
    }
    @RequestMapping(value = "jenisSurat/add", method = RequestMethod.POST)
    public String addJenisSuratSubmit(@ModelAttribute JenisSuratModel jenisSurat, Model model){

        if(jenisSuratService.getJenisSuratByNama(jenisSurat.getNama()) == null){
            jenisSuratService.addJenisSurat(jenisSurat);
            model.addAttribute("nama", jenisSurat.getNama());
            model.addAttribute("message","Berhasil menambahkan jenis surat");
            return "successPage-jenis-surat";
        }else{
            model.addAttribute("nama", jenisSurat.getNama());
            model.addAttribute("message","Sudah terdapat jenis surat ini didala sistem");
            return "failPage-jenis-surat";
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
            model.addAttribute("message","Jenis surat berhasil dihapus");
            model.addAttribute("nama", nama);
            return "succesPage-jenis-surat";
        }
    }
}
