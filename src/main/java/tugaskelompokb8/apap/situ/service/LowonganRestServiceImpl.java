package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.LowonganModel;
import tugaskelompokb8.apap.situ.repository.LowonganDb;
import tugaskelompokb8.apap.situ.rest.BaseResponse;
import tugaskelompokb8.apap.situ.rest.BaseRest;
import tugaskelompokb8.apap.situ.rest.Setting;
import tugaskelompokb8.apap.situ.rest.UserPerpusDetail;

import javax.transaction.Transactional;

@Service
public class LowonganRestServiceImpl implements LowonganRestService {

    private final WebClient webClient;

    @Autowired
    LowonganDb lowonganDb;


//    @Override
//    public LowonganDetail getLowonganById(Long id) {
//        LowonganModel lowongan =  lowonganDb.findByIdLowongan(id);
//        LowonganDetail pengajuanApi = new LowonganDetail();
//        pengajuanApi.setIdUser(lowongan.getUser().getIdUser());
//        pengajuanApi.setJenisLowongan(lowongan.getJenisLowongan().getNama());
//        pengajuanApi.setKeterangan(lowongan.getKeterangan());
//        pengajuanApi.setJudul(lowongan.getJudul());
//        pengajuanApi.setJumlah(lowongan.getJumlah());
//        pengajuanApi.setTanggalDibuka(lowongan.getTanggalDibuka());
//        pengajuanApi.setTanggalDitutup(lowongan.getTanggalDitutup());
//        return pengajuanApi;
//    }


    @Override
    public LowonganModel createLowongan(LowonganModel lowongan) {
        return lowonganDb.save(lowongan);
    }

    public LowonganRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.userPerpusURL).build();
    }
    @Override
    public Mono<BaseResponse> getAllUser(){
            String uri = "/api/user/all";
            return this.webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(BaseResponse.class);
    }


}


