package com.example.demo.Controller.User;

import com.example.demo.DAO.InteractionDAO;
import com.example.demo.Model.Interaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class InteractionController {
    private final static InteractionDAO interactionDAO= new InteractionDAO();

    @PostMapping("/vote")
    public String updateInteraction(@RequestBody Interaction interaction) throws SQLException {

        interactionDAO.setInteraction(interaction.getPostId(),interaction.getUserId(),interaction.getType());
        return "done";
    }
}
