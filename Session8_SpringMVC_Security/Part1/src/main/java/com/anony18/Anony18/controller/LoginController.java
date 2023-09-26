package com.anony18.Anony18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showLogin(){
        return "fancy-login";
    }
}
