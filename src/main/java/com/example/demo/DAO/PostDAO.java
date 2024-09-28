package com.example.demo.DAO;

import com.example.demo.Model.PostModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PostDAO {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/BlogDB";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345";
    
    private static final String ADD_A_POST = "INSERT INTO post(postId,userId,title,tags,type,content,timeUp) VALUES(?,?,?,?,?,?,NOW())";
    public PostDAO(){}
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean addPost(PostModel post){
        try {
            System.out.println("Hello world");
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_A_POST);
            ps.setString(1,post.getPostId());
            ps.setString(2,post.getUserId());
            ps.setString(3,post.getTitle());
            ps.setString(4,post.getTags());
            ps.setString(5,post.getType());
            ps.setString(6,post.getContent());

            ps.execute();
            ps.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}

