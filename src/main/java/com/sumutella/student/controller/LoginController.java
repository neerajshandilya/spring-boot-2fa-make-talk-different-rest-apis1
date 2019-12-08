package com.sumutella.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sumutella
 * @time 8:26 PM
 * @since 12/8/2019, Sun
 */
@Controller
public class LoginController {
    @GetMapping("/my-login")
    public String showLogin(){
        return "login";
    }


    @GetMapping("/index")
    public String showIndex(){
        return "index";
    }
}
