package tugaskelompokb8.apap.situ.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tugaskelompokb8.apap.situ.rest.BaseRest;
import tugaskelompokb8.apap.situ.rest.UserSivitasList;
import tugaskelompokb8.apap.situ.service.UserRestService;

import java.util.List;

@RestController
@RequestMapping(value="/api/user")
public class UserRestController {
    @Autowired
    private UserRestService userRestService;

    @GetMapping("/list")
    public BaseRest<List<UserSivitasList>> retrieveUser(){
        BaseRest<List<UserSivitasList>> respon = new BaseRest<>();
        respon.setStatus(200);
        respon.setMessage("success");
        respon.setResult(userRestService.retrieveUser());
        return respon;
    }

}
