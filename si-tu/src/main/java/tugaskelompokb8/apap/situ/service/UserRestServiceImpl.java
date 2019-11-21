package tugaskelompokb8.apap.situ.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.rest.Setting;
import tugaskelompokb8.apap.situ.rest.UserDetail;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{
	
	@Autowired
	UserDb userDb;
	//customer service
	
//	public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
//		this.webClient = webClientBuilder.baseUrl(Setting.userURL).build();
//	}
	

	@Override
	public UserModel getUserById(String id) {
		return userDb.findByIdUser(id);
	}

	static RestTemplate restTemplate = new RestTemplate();
	
	@Override
    public Object getUser(String idUser) {
        ResponseEntity<Object> user = restTemplate.getForEntity(Setting.userURL +  idUser, Object.class);
        return user.getBody();
    }
	
}
