package com.example.demo.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/Post")
public class PostController {
    @GetMapping
    public String Post(){
        return "admin/Post";
    }
}