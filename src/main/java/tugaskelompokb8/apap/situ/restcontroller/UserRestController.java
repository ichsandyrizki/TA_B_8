package tugaskelompokb8.apap.situ.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.rest.Setting;
import tugaskelompokb8.apap.situ.rest.UserDetail;

import tugaskelompokb8.apap.situ.service.UserRestService;

@RestController
@RequestMapping(value="/api/v1/situ")
public class UserRestController {

	@Qualifier("userRestServiceImpl")
	@Autowired
	UserRestService userRestService;


}
