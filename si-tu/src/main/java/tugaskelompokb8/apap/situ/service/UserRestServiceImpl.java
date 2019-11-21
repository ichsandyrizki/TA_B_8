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
    public Object getUser(String idUser, long idRole) {
		if(idRole == 3){
			ResponseEntity<Object> guru = restTemplate.getForEntity(Setting.guruURL +  idUser, Object.class);
			return guru.getBody();
		} else if (idRole == 4){
			ResponseEntity<Object> siswa = restTemplate.getForEntity(Setting.siswaURL +  idUser, Object.class);
			return siswa.getBody();
		} else {
			ResponseEntity<Object> pegawai = restTemplate.getForEntity(Setting.pegawaiURL +  idUser, Object.class);
			return pegawai.getBody();
		}
    }
	
}
