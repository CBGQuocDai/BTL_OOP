package com.example.demo.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DAO.PostDAO;

@Controller
public class PostController {
    @Autowired
    PostDAO postDAO;

    @RequestMapping("/admin_post")
    public String Post(Model model){
        model.addAttribute("posts" , postDAO.getAllPost());
        return "admin/Post";
    }

    @RequestMapping("/delete-post")
    public String deleteUser(Model model , @RequestParam("id") String userId) throws Exception {
        postDAO.deletePostById(userId);
        return "redirect:/admin_post";
    }

    @RequestMapping("/post-view/{id}")
    public String PostView(Model model, @RequestParam("id") int postId) throws Exception {
        model.addAttribute("post", postDAO.selectPostById(postId));
        return "admin/post-view";
    }
}