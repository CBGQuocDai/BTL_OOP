package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InteractionController {

    @PostMapping("/updateVote")
    public String updateVote(){
        return "";
    }
}
