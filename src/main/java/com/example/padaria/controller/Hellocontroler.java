package com.example.padaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/hello")
public class Hellocontroler {

    @GetMapping
    public String home(Model model, Principal principal) {

        return "hello";
    }
}
