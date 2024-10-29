/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.example.demo.Controller.User;
//
//import com.example.demo.DAO.PostDAO;
//import com.example.demo.DAO.UserDAO;
//import com.example.demo.Model.Post;
//import com.example.demo.Model.User;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// *
// * @author Admin
// */
//@Controller
//@RequestMapping("/My_Profile")
//public class MyProfileController {
//
//    @Autowired
//    private static UserDAO userDAO;
//    @Autowired
//    private static PostDAO postDAO;
//
//    @GetMapping("/userInfo")
//    public String userInfo(Model model) throws SQLException {
//        // Giả sử userId = 2 cho ví dụ
//        User user = userDAO.getUserByUserId(2);
//        model.addAttribute("user", user);
//        return "userInfo";
//    }
//
//    @PostMapping("/userInfo")
//    public String updateUserInfo(@ModelAttribute User user) throws SQLException {
//        userDAO.updateUser(user);
//        return "redirect:/My_Profile/userInfo";
//    }
//
//    @GetMapping("/question")
//    public String question(Model model) {
//        int userId = 4;
//        List<Post> posts = postDAO.getPostsByUserId(userId);
//        model.addAttribute("posts", posts);
//        return "profile_question";
//    }
//
//    @GetMapping("/post")
//    public String postIndex(Model model) {
//        int userId = 4;
//        List<Post> posts = postDAO.getPostsByUserId(userId);
//        model.addAttribute("posts", posts);
//        return "profile_post";
//    }
//
//    @GetMapping("/ChangePassword")
//    public String changePassword(Model model) throws SQLException {
//        int userId = 2;
//        User user = userDAO.getUserByUserId(userId);
//        model.addAttribute("user", user);
//        return "profile_change_password";
//    }
//
//    @PostMapping("/ChangePassword")
//    public String handlePasswordChange(
//            @RequestParam("password") String oldPassword,
//            @RequestParam("newPassword") String newPassword,
//            @RequestParam("confirmPassword") String confirmPassword) throws SQLException {
//
//        int userId = 2;
//        User user = userDAO.getUserByUserId(userId);
//
//        if (!user.getPassword().equals(oldPassword)) {
//            return "Vui lòng nhập mật khẩu khác mật khẩu cũ kkk";
//        }
//
//        if (!newPassword.equals(confirmPassword)) {
//            return "Mật khẩu không trùng khớp";
//        }
//
//        user.setPassword(newPassword);
//        userDAO.updateUser(user);
//
//        return "redirect:/My_Profile/userInfo?success=Password changed successfully";
//    }
//
//}

//Dùng cái dưới này khi userId được lưu vào session

package com.example.demo.Controller.User;
import com.example.demo.DAO.PostDAO;
import com.example.demo.DAO.UserDAO;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/My_Profile")
public class MyProfileController {
    private final UserDAO userDAO;
    private final PostDAO postDAO;

    public MyProfileController(UserDAO userDAO, PostDAO postDAO) {
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model, HttpSession session) throws SQLException {
        // Lấy userId từ session
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }
        User user = userDAO.getUserByUserId(userId);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        model.addAttribute("avatarUser","/file/"+userId+".png");
        return "userInfo";
    }

    @PostMapping("/userInfo")
    public String updateUserInfo(@ModelAttribute User user, HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }
        user.setUserId(userId); // Gán userId từ session
        userDAO.updateUser(user);
        return "redirect:/My_Profile/userInfo";
    }

    @GetMapping("/question")
    public String question(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }
        List<Post> questions = postDAO.getQuestionsByUserId(userId);
        model.addAttribute("posts", questions);
        model.addAttribute("userId", userId);
        model.addAttribute("avatarUser","/file/"+userId+".png");
        return "profile_question";
    }

    @GetMapping("/post")
    public String postIndex(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }
        List<Post> posts = postDAO.getPostsByUserId(userId);
        model.addAttribute("posts", posts);
        model.addAttribute("userId", userId);
        model.addAttribute("avatarUser","/file/"+userId+".png");
        return "profile_post";
    }

    @GetMapping("/ChangePassword")
    public String changePassword(Model model, HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }
        User user = userDAO.getUserByUserId(userId);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        model.addAttribute("avatarUser","/file/"+userId+".png");
        return "profile_change_password";
    }

    @PostMapping("/ChangePassword")
    public String handlePasswordChange(
            @RequestParam("password") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session) throws SQLException {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
        }

        User user = userDAO.getUserByUserId(userId);

        if (!user.getPassword().equals(oldPassword)) {
            return "Vui lòng nhập mật khẩu khác mật khẩu cũ";
        }

        if (!newPassword.equals(confirmPassword)) {
            return "Mật khẩu không trùng khớp";
        }

        user.setPassword(newPassword);
        userDAO.updateUser(user);

        return "redirect:/My_Profile/userInfo?success=Password changed successfully";
    }

}

