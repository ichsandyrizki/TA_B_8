package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.rest.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PinjamanRestServiceImpl implements PinjamanRestService{

    private final WebClient webClient;

    public PinjamanRestServiceImpl(WebClient.Builder webClientBuild) {
        this.webClient = webClientBuild
                .baseUrl(Setting.pengajuanURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    static RestTemplate restTemplate = new RestTemplate();

    @Override
    public Mono<BaseRest> registerPinjaman (PinjamanModel pinjaman){
        Map<String, String> data = new HashMap<String, String>();
        data.put("tanggalPengajuan", String.valueOf(pinjaman.getTanggalPengajuan()));
        data.put("jumlahPeminjaman", String.valueOf(pinjaman.getJumlahPeminjaman()));
        String uri = "";


        return this.webClient
                .post()
                .uri(uri)
                .syncBody(data)
                .retrieve()
                .bodyToMono(BaseRest.class);

    }
}
