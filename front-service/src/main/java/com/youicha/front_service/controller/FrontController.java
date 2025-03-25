package com.youicha.front_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping("/patients")
    public String showPatients(Model model){
        return "patients";
    }
}
