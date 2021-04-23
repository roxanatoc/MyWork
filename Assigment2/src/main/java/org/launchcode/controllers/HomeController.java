package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("user")
    public String index(){
        return "index";
    }

    @GetMapping("admin")
    public String index2(){
        return "index2";
    }
}
