package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.AccountService;

@Controller
public class RegisterController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("/register")
    public ModelAndView register(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
        if (accountService.newAccount(username, password)) {
            mav.setViewName("redirect:/login");
        } else {
            mav.addObject("error", true);
            mav.setViewName("register.html");
        }
        return mav;
    }
}
