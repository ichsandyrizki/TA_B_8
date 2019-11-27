package tugaskelompokb8.apap.situ.service;
import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.rest.BaseResponse;
import tugaskelompokb8.apap.situ.rest.UserDetail;
import tugaskelompokb8.apap.situ.rest.BaseRest;
import tugaskelompokb8.apap.situ.rest.PinjamanModel;

public interface PinjamanRestService {
    Mono<BaseRest> registerPinjaman (PinjamanModel pinjaman);
}
