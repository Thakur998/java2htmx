package com.j2htmx.auto.controller;

import com.j2htmx.auto.components.Label;
import com.j2htmx.auto.demo.beans.LoginDetail;
import com.j2htmx.auto.demo.pages.Dashboard;
import com.j2htmx.auto.demo.pages.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @Autowired
    public LoginPage loginPage;

    @Autowired
    public Dashboard dashboard;

    @GetMapping("/login")
    public String loginPage() {
        return loginPage.render();
    }

    @PostMapping("/login-user")
    public String login(@ModelAttribute LoginDetail loginDetail) {
        return dashboard.render();
    }
}