package tugaskelompokb8.apap.situ.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.rest.Setting;
import tugaskelompokb8.apap.situ.rest.UserPerpusDetail;
import tugaskelompokb8.apap.situ.service.LowonganRestService;

import java.io.InputStream;

@RestController
@RequestMapping(value="api/v1/situ")
public class LowonganRestController {
    @Autowired
    LowonganRestService lowonganRestService;

    @Autowired
    RestTemplate restTemplate;

//    @GetMapping(value="/lowongan/{idLowongan}")
//    public LowonganDetail retrieveLowongan(@PathVariable("idLowongan") Long idLowongan) {
//        try {
//            return lowonganRestService.getLowonganById(idLowongan);
//        }
//        catch(NoSuchElementException e){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,"ID Lowongan " + idLowongan + " Not Found");
//        }
//    }
//
//    @PostMapping(value="/lowongan/add")
//    public LowonganModel createLowongan(@Valid @RequestBody LowonganModel lowongan,
//                                             BindingResult bindingResult) {
//        if(bindingResult.hasFieldErrors()) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,"Request body has invalid type or missing field");
//
//        }else {
//            return lowonganRestService.createLowongan(lowongan);
//        }
//    }

    @GetMapping("/users")
    private String getAllUsers() {
        String url = Setting.userPerpusURL;

        return restTemplate.getForObject(url, String.class);
    }

//    @GetMapping("/user/{role}/role")
//    private Mono<UserPerpusDetail> getUser(@PathVariable String role){
//        return lowonganRestService.getUser(role);
//    }


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
