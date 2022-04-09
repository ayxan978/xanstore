package com.project.xanstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/loginForAdmin")
    public String getLoginPage() {
        return "/login";
    }

}
