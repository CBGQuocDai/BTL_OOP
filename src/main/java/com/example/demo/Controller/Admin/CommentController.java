package com.example.demo.Controller.Admin;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DAO.CommentDAO;

@Controller
public class CommentController {

    @Autowired
    private CommentDAO commentDAO;

    @RequestMapping("/admin_comment")
    public String Post(Model model) throws SQLException {
        model.addAttribute("comments" , commentDAO.getAllComment());
        return "admin/Comment";
    }

    @RequestMapping("/delete-comment")
    public String deleteUser(Model model , @RequestParam("id") String userId) throws Exception {
        commentDAO.deleteCommentById(userId);
        return "redirect:/admin_comment";
    }

    @RequestMapping("/comment-view/{id}")
    public String CommentView(Model model, @RequestParam("id") int commentId) throws Exception {
        commentDAO.selectCommentById(commentId);
        return "admin/comment-view";
    }
}