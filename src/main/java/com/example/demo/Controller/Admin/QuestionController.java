package com.example.demo.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/Question")
public class QuestionController {
    @GetMapping
    public String Question(){
        return "admin/Question";
    }
}