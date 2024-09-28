package com.example.demo.Controller;

import com.example.demo.DAO.PostDAO;
import com.example.demo.Model.PostModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//@RequestMapping("/Post")
public class PostController {
    static int postId=0;
    static boolean missingTitle=false,missingTags=false,missingContent=false;
    private PostModel post=new PostModel();
    @GetMapping("/create")
    public String create(ModelMap modelMap){
        modelMap.addAttribute("Post",post);
        modelMap.addAttribute("missingTitle",missingTitle);
        modelMap.addAttribute("missingTags",missingTags);
        modelMap.addAttribute("missingContent",missingContent);
        return "create";
    }

    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("Post") PostModel postSubmit , BindingResult result){
        PostDAO postDAO =new PostDAO();
        missingContent = result.hasFieldErrors("content");
        missingTags = result.hasFieldErrors("tags");
        missingTitle = result.hasFieldErrors("title");
        if(!result.hasErrors()) {
            postId++;
            String ID = String.valueOf(postId);
            while (ID.length() < 5) {ID = "0" + ID;}
            ID = "P" + ID;
            postSubmit.setPostId(ID);
            postDAO.addPost(postSubmit);
            post= new PostModel();
            return "redirect:/hhh";
        }
        else {
            post= postSubmit;
            return "redirect:/create";
        }
    }
}
