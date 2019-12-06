package tugaskelompokb8.apap.situ.service;

import tugaskelompokb8.apap.situ.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import javax.transaction.Transactional;

@Service
@Transactional
public class PengajuanPinjamanServiceImpl implements PengajuanPinjamanService {

    private WebClient webClient;

    public PengajuanPinjamanServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.koperasiURL).build();
    }

    @Override
    public Mono<String> postPinjamanKoperasi(){
        MultiValueMap<String, String> data = new LinkedMultiValueMap<String, String>();
        data.add("test","test");
        return this.webClient.post().uri("/rest/pinjaman")
                .syncBody(data)
                .retrieve()
                .bodyToMono(String.class);
    }



}
