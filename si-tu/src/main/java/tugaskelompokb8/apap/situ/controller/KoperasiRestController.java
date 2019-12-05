package tugaskelompokb8.apap.situ.controller;

import java.sql.Date;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.rest.BaseRest;
import tugaskelompokb8.apap.situ.rest.PinjamanDetail;
import tugaskelompokb8.apap.situ.service.PinjamanRestService;
import tugaskelompokb8.apap.situ.service.UserService;
import reactor.core.publisher.Mono;

@Controller
public class KoperasiRestController{

    @Autowired
    PinjamanRestService pinjamanRestService;

    @Autowired
    UserService userService;

    @GetMapping("/pinjaman/tambah")
    private String addPeminjamanForm(Model model) {
        PinjamanDetail pinjamanDetail = new PinjamanDetail();
        model.addAttribute("pinjamanDetail", pinjamanDetail);

        return "pinjaman";
    }

    @PostMapping(value = "/pinjaman/tambah")
    private String pinjamanSubmit(@ModelAttribute @Valid PinjamanDetail form,
                                    Model model){
        Mono<BaseRest> api;
        PinjamanDetail pinjaman = new PinjamanDetail();
        pinjaman.setJumlahPinjaman(form.getJumlahPinjaman());
        pinjaman.setTanggalPengajuan(form.getTanggalPengajuan());
        String uuid = userService.getUserCurrentLoggedIn().getIdUser();

        api = pinjamanRestService.registerPinjaman(pinjaman, uuid);

        if (api.block().getStatus() == 200){
            model.addAttribute("message", "Peminjaman berhasil dibuat!");
            model.addAttribute("type", "alert-success");
            return "pinjaman";
        }
        else {
            return "500";
        }
    }
}