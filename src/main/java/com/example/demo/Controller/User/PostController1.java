package com.example.demo.Controller.User;

import com.example.demo.DAO.PostDAO;
import com.example.demo.Model.Post;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/Post")
public class PostController1 {
    static int postId=0;
    static boolean missingTitle=false,missingTags=false,missingContent=false;
    private final PostDAO postDAO =new PostDAO();
    private Post post=new Post();
    private Post pre_post=new Post();
    @GetMapping("/create")
    public String create(ModelMap modelMap){
        post=pre_post;
        modelMap.addAttribute("Post",post);
        modelMap.addAttribute("missingTitle",missingTitle);
        modelMap.addAttribute("missingTags",missingTags);
        modelMap.addAttribute("missingContent",missingContent);
        return "create";
    }
    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("Post") com.example.demo.Model.Post postSubmit , BindingResult result){
        missingContent = result.hasFieldErrors("content");
        missingTags = result.hasFieldErrors("tags");
        missingTitle = result.hasFieldErrors("title");
        if(!result.hasErrors()) {
            postSubmit.setPostId(postId);
            postDAO.addPost(postSubmit);
            post=new com.example.demo.Model.Post();
            pre_post = new com.example.demo.Model.Post();
            return "redirect:/Post/"+postId;
        }
        else {
            pre_post= postSubmit;
            return "redirect:/Post/create";
        }
    }

    @GetMapping("/{id}")
    public String postDetail(ModelMap modelMap, @PathVariable String id){
        post = postDAO.selectPostById(Integer.parseInt(id));
        modelMap.addAttribute("post",post);
        String[] tags= post.getTags().split(",");
        modelMap.addAttribute("tags",tags);
        return "postDetail";
    }
}