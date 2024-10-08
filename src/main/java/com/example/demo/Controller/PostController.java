package com.example.demo.Controller;

import com.example.demo.DAO.PostDAO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/Post")
public class PostController {
    static int postId=0;
    static boolean missingTitle=false,missingTags=false,missingContent=false;
    private PostDAO postDAO =new PostDAO();
    private com.example.demo.Model.Post post=new com.example.demo.Model.Post();
    private com.example.demo.Model.Post pre_post=new com.example.demo.Model.Post();
    @GetMapping("/create")
    public String create(ModelMap modelMap, HttpSession httpSession){
        post=pre_post;
        modelMap.addAttribute("Post",post);
        modelMap.addAttribute("missingTitle",missingTitle);
        modelMap.addAttribute("missingTags",missingTags);
        modelMap.addAttribute("missingContent",missingContent);
        return "create";
    }
    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("Post") com.example.demo.Model.Post postSubmit , BindingResult result, HttpSession httpSession){
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
            post=new com.example.demo.Model.Post();
            pre_post = new com.example.demo.Model.Post();
            return "redirect:/Post/"+ID;
        }
        else {
            pre_post= postSubmit;
            return "redirect:/Post/create";
        }
    }

    @GetMapping("/{id}")
    public String postDetail(ModelMap modelMap, @PathVariable String id, HttpSession httpSession){

        post = postDAO.selectPostById(id);
        modelMap.addAttribute("post",post);
        String[] tags= post.getTags().split(",");
        modelMap.addAttribute("tags",tags);
        return "postDetail";
    }
}
