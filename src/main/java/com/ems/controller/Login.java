package com.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {
    @GetMapping("/login")
    public String showLoginPage() {
        return "/homefiles/login.html"; // This should map to your login.html in the templates or static folder
    }
}