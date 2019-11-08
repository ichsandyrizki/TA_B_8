package tugaskelompokb8.apap.situ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.service.JenisSuratService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PengajuanSuratController {

    @Autowired
    JenisSuratService jenisSuratService;
    @RequestMapping(value = "/pengajuanSurat/tambah", method = RequestMethod.GET)
    public String addPasienFormPage(Model model) {
        PengajuanSuratModel suratBaru = new PengajuanSuratModel();
        suratBaru.setTanggalDisetujui(null);
        suratBaru.setStatus(0);
        suratBaru.setNomorSurat("0");
        suratBaru.setTanggalPengajuan(Date.valueOf(LocalDate.now()));
        List<JenisSuratModel> jenisSuratList = jenisSuratService.getJenisSuratList();
        model.addAttribute("surat", suratBaru);
        model.addAttribute("jenisSuratList", jenisSuratList);
        return "form-pengajuan-surat";
    }
}
