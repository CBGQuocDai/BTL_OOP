package com.example.demo.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/Comment")
public class CommentController {
    @GetMapping
    public String Comment(){
        return "admin/Comment";
    }
}