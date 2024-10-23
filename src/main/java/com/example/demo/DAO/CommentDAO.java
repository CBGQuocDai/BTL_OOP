package com.example.demo.DAO;

import com.example.demo.Model.Comment;

import java.awt.geom.RectangularShape;
import java.sql.*;
import java.util.ArrayList;

public class CommentDAO {
    private String jdbcURL = "jdbc:mysql://mysql-4bc7aa-quocdaicbg001-d224.c.aivencloud.com:16253/defaultdb";
    private String jdbcUsername = "avnadmin";
    private String jdbcPassword = "AVNS_jfijrHh9AlwIpwNz30Z";

    private final String ADD_COMMENT= "INSERT INTO comment(commentId,parentComment,postId,username,content,timeUP) VALUES(?,?,?,?,?,NOW())";
    private final String GET_ALL_COMMENT_BY_POSTID="SELECT * FROM comment WHERE postId= ?";
    private final String GET_ALL_COMMENT="SELECT * FROM comment";
    private final String DELETE_COMMENT="DELETE FROM comment WHERE commentId = ?";

    private final String COUNT_COMMENT_OF_POST ="SELECT COUNT(*) FROM comment where postId= ?";
    public CommentDAO(){}
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
    public void deleteCommentById (String id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_COMMENT);
        ps.setString(1 , id);
        ps.executeUpdate();
    }


    public ArrayList<Comment> getAllComment() throws SQLException {
        Connection connection =getConnection();
        PreparedStatement ps= connection.prepareStatement(GET_ALL_COMMENT);
        ResultSet rs= ps.executeQuery();
        ArrayList<Comment> cmts= new ArrayList<>();

        while(rs.next()){
            Comment cmt =new Comment();
            cmt.setCommentId(rs.getInt("commentId"));
            cmt.setParentComment(rs.getInt("parentComment"));
            cmt.setPostId(rs.getInt("postId"));
            cmt.setUsername(rs.getString("username"));
            cmt.setContent(rs.getString("content"));
            cmt.setTimeUp(rs.getTimestamp("timeUp"));
            cmts.add(cmt);
        }
        return cmts;
    }


    public int countNumberComment(int postId){
        try {
            Connection connection=getConnection();
            PreparedStatement ps= connection.prepareStatement(COUNT_COMMENT_OF_POST);
            ps.setString(1,String.valueOf(postId));
            ResultSet rs= ps.executeQuery();
            if(rs.next())
            {
                return rs.getInt("count(*)");

            }
            else return 0;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addComment(Comment cmt) throws SQLException {

        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(ADD_COMMENT);
        ps.setString(1,String.valueOf(cmt.getCommentId()));
        ps.setString(2,String.valueOf(cmt.getParentComment()));
        ps.setString(3,String.valueOf(cmt.getPostId()));
        ps.setString(4,cmt.getUsername());
        ps.setString(5,cmt.getContent());
        ps.execute();

    }
    public ArrayList<Comment> getAllCommentByPostId(String postId) throws SQLException {
        Connection connection =getConnection();
        PreparedStatement ps= connection.prepareStatement(GET_ALL_COMMENT_BY_POSTID);
        ps.setString(1,postId);
        ResultSet rs= ps.executeQuery();
        ArrayList<Comment> cmts= new ArrayList<>();

        while(rs.next()){
            Comment cmt =new Comment();
            cmt.setCommentId(rs.getInt("commentId"));
            cmt.setParentComment(rs.getInt("parentComment"));
            cmt.setPostId(rs.getInt("postId"));
            cmt.setUsername(rs.getString("username"));
            cmt.setContent(rs.getString("content"));
            cmt.setTimeUp(rs.getTimestamp("timeUp"));
            cmts.add(cmt);
        }
        return cmts;
    }
}
