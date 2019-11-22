package tugaskelompokb8.apap.situ.service;
import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.rest.BaseResponse;
import tugaskelompokb8.apap.situ.rest.UserDetail;
import tugaskelompokb8.apap.situ.rest.BaseRest;
import tugaskelompokb8.apap.situ.rest.UserSivitasModel;

public interface UserRestService {
	UserModel getUserById(String id);
	Mono<BaseResponse> getUserData(String idUser, long idRole);
    Mono<BaseRest> registerUser (UserSivitasModel userSivitasModel);
}
