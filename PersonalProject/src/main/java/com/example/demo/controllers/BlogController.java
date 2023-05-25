package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Account;
import com.example.demo.services.AccountService;

@Controller
public class BlogController {
    private AccountService accountService;

    public BlogController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/blog")
    public String index() {
        return "blog.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
        if (accountService.newAccount(username, password)) {
            mav.addObject("user", username);
            mav.setViewName("blog.html");
        } else {
            mav.addObject("error", "error");
            mav.setViewName("login.html");
        }
        return mav;
    }
}
