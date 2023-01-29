package com.projekt.planLekcji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {

    public HomeWebController() {
    }

    @GetMapping("/")
    public String home() {
        return "/home";
    }

}
