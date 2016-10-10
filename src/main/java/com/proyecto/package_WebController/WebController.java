package com.proyecto.package_WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DanielaMartin on 10/10/16.
 */
@Controller
public class WebController {
    @RequestMapping("/")
    public String hola(){
        return "index";
    }

}
