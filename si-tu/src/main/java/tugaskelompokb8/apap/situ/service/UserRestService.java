package tugaskelompokb8.apap.situ.service;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.rest.BaseRest;
import tugaskelompokb8.apap.situ.rest.UserSivitasList;
import tugaskelompokb8.apap.situ.rest.UserSivitasModel;

import java.util.List;

public interface UserRestService {
    Mono<BaseRest> registerUser (UserSivitasModel userSivitasModel);
}
