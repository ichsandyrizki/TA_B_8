package tugaskelompokb8.apap.situ.service;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.rest.BaseRest;
import tugaskelompokb8.apap.situ.rest.UserSivitasModel;

public interface UserRestService {
    Mono<BaseRest> registerUser (UserSivitasModel userSivitasModel);
}
