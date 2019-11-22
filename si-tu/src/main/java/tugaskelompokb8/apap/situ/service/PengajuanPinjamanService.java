package tugaskelompokb8.apap.situ.service;
import reactor.core.publisher.Mono;

public interface PengajuanPinjamanService {
    Mono<String>  postPinjamanKoperasi();
}
