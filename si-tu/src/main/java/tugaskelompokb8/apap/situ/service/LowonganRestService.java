package tugaskelompokb8.apap.situ.service;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.LowonganModel;
import tugaskelompokb8.apap.situ.rest.UserPerpusDetail;

public interface LowonganRestService {
//    LowonganDetail getLowonganById(Long id);
    LowonganModel createLowongan(LowonganModel lowongan);
    Mono<UserPerpusDetail> getUser(String role);
}
