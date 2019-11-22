package tugaskelompokb8.apap.situ.controller;

//import apap.tutorial.gopud.model.MenuModel;
//import apap.tutorial.gopud.model.RestoranModel;
//import apap.tutorial.gopud.rest.RestoranDetail;
//import apap.tutorial.gopud.service.RestoranRestService;
//import apap.tutorial.gopud.service.RestoranService;

import tugaskelompokb8.apap.situ.model.*;
import tugaskelompokb8.apap.situ.repository.*;
import tugaskelompokb8.apap.situ.rest.*;
import tugaskelompokb8.apap.situ.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;

@RestController
@RequestMapping("/sikop")
public class KoperasiRestController {

    @Autowired
    private PengajuanPinjamanService pengajuanPinjamanService;

    @PostMapping(value = "/addPengajuan")
    private Mono<String> postStatus(){
        return pengajuanPinjamanService.postPinjamanKoperasi();
    }



}
