package com.anony18.springboot.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    // add an initbinder ... to convert trim input strings
    // remove leading and traiing whitespace
    // resolve issue for our validation

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showForm(Model theModel) {
        theModel.addAttribute("customer", new Customer());
        return "customer-form"; // thymeleaf
    }

    @PostMapping("/processForm")
    public String processForm(
            @Valid // tell Spring MVC to perform validation
            @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult) {

        System.out.println("Binding result: " + theBindingResult.toString());
        System.out.println("\n\n\n");


        if (theBindingResult.hasErrors()) {
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }
}
