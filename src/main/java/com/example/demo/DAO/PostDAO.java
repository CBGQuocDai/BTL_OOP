package com.example.demo.DAO;

import com.example.demo.Model.Post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PostDAO {
    

    private String jdbcURL = "jdbc:mysql://mysql-4bc7aa-quocdaicbg001-d224.c.aivencloud.com:16253/defaultdb";
    private String jdbcUsername = "avnadmin";
    private String jdbcPassword = "AVNS_jfijrHh9AlwIpwNz30Z";
    
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

    public boolean addPost(Post post){
        try {
            System.out.println("Hello world");
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_A_POST);
            ps.setString(1,String.valueOf(post.getPostId()));
            ps.setString(2,String.valueOf(post.getUserId()));
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

