package com.anony18.Anony18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    // add a request mapping for /leaders
    @GetMapping("/leaders")
    public String showLeaders(){
        return "leaders";
    }

    // add a request mapping for /system
    @GetMapping("/systems")
    public String showSystem(){
        return "systems";
    }

    // add a request mapping for /system
    @GetMapping("/access-denied")
    public String showAccess_denied(){
        return "access-denied";
    }
}
