package com.example.demo.DAO;

import com.example.demo.Model.Post;

import java.sql.*;

public class PostDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/BlogDB";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345";
    
    private static final String ADD_A_POST = "INSERT INTO post(postId,userId,title,tags,type,content,timeUp) VALUES(?,?,?,?,?,?,NOW())";
    private static final String GET_POST_BY_ID= "SELECT * FROM post WHERE postId = ?";

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

    public void addPost(Post post){
        try {
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Post selectPostById(int id){
        Post post= new Post();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_POST_BY_ID);
            ps.setString(1,String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                post.setPostId(rs.getInt("postId"));
                post.setType(rs.getString("type"));
                post.setTags(rs.getString("tags"));
                post.setContent(rs.getString("content"));
                post.setTitle(rs.getString("title"));
                post.setTimeUp(Timestamp.valueOf(rs.getString("timeUp")));
                post.setUserId(rs.getInt("userId"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return post;
    }

}

