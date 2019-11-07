package tugaskelompokb8.apap.situ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    private String home(){
        return "index";
    }
}
