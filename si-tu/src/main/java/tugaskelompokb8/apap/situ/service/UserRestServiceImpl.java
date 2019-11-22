package tugaskelompokb8.apap.situ.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.rest.BaseResponse;
import tugaskelompokb8.apap.situ.rest.Setting;
import tugaskelompokb8.apap.situ.rest.UserDetail;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{

    private final WebClient webClient;

	@Autowired
	UserDb userDb;

	@Autowired
    UserService userService;


    public UserRestServiceImpl(WebClient.Builder webClientBuild){
        this.webClient = webClientBuild
                .baseUrl(Setting.Sivitas)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

	@Override
	public UserModel getUserById(String id) {
		return userDb.findByIdUser(id);
	}

    static RestTemplate restTemplate = new RestTemplate();

    @Override
    public Mono<BaseResponse> getUserData(String idUser, long idRole) {
        if(idRole == 2){
            String uri = "/api/employees/" + idUser;
            return this.webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(BaseResponse.class);

        } else if(idRole == 3 || idRole == 1){
            String uri = "/api/teachers/" + idUser;
            return this.webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(BaseResponse.class);
        }else
            {
            String uri = "/api/students/" + idUser;
            return this.webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(BaseResponse.class);
        }
    }
}
