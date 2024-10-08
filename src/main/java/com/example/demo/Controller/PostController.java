package com.example.demo.Controller;

import com.example.demo.DAO.PostDAO;
import com.example.demo.Model.Post;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
//@RequestMapping("/Post")
public class PostController {
    static int postId=0;
    static boolean missingTitle=false,missingTags=false,missingContent=false;
    private Post post=new Post();
    PostDAO postDAO =new PostDAO();
    @GetMapping("/create")
    public String create(ModelMap modelMap){
        modelMap.addAttribute("Post",post);
        modelMap.addAttribute("missingTitle",missingTitle);
        modelMap.addAttribute("missingTags",missingTags);
        modelMap.addAttribute("missingContent",missingContent);
        return "create";
    }

    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("Post") Post postSubmit , BindingResult result){

        missingContent = result.hasFieldErrors("content");
        missingTags = result.hasFieldErrors("tags");
        missingTitle = result.hasFieldErrors("title");
        if(!result.hasErrors()) {
            postId++;
            postSubmit.setPostId(postId);
            postDAO.addPost(postSubmit);
            post= new Post();
            return "redirect:/hhh";
        }
        else {
            post= postSubmit;
            return "redirect:/create";
        }
    }
}
