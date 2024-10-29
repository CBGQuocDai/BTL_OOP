package com.example.demo.Controller.User;

import com.example.demo.DAO.UserDAO;
import com.example.demo.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class LoginController {
    private final UserDAO userDAO =new UserDAO();
    @GetMapping("/login")
    public String login(ModelMap modelMap){
        modelMap.addAttribute("user",new User());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User userC, BindingResult result, HttpSession httpSession) throws SQLException {
        boolean missingUsername =result.hasFieldErrors("username");
        boolean missingPassword =result.hasFieldErrors("password");
        if(!missingPassword && !missingUsername){
            User user= userDAO.getUserByUsername(userC.getUsername());
            if(user.getUsername()== null) return "redirect:/login";
            if( user.getPassword().equals(userC.getPassword())){
                if(user.getRole().equals("user")){
                    httpSession.setAttribute("username",user.getUsername());
                    httpSession.setAttribute("userId",user.getUserId());
                    return "redirect:/post/latest";
                }
                else return "redirect:/admin";
            }
            else return "redirect:/login";
        }
        else{
            return "redirect:/login";
        }

    }
    @GetMapping("/signup")
    public String Signup(){
        return "signup";
    }

}
