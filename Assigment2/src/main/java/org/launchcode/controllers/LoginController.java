package org.launchcode.controllers;

import org.launchcode.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String loginScene(Model model){
        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping
    public String processBooks(@RequestParam(required = false) int[] bookIds){

        if(bookIds != null) {
            for (int id : bookIds) {
                userRepository.findById(id);
            }
        }
        return "redirect:user";
    }

}
