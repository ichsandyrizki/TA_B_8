package tugaskelompokb8.apap.situ.service;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.rest.PinjamanDetail;
import tugaskelompokb8.apap.situ.rest.BaseRest;

public interface PinjamanRestService {
    Mono<BaseRest> registerPinjaman (PinjamanDetail pinjaman, String uuid);
}
