package com.example.demo.DAO;

import com.example.demo.Model.Report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReportDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/BlogDB";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345";


    public ReportDAO(){}
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

    private final static String ADD_REPORT ="INSERT INTO report(postId,commentId,reason,content,time) VALUES(?,?,?,?,NOW())";
    public void addReport(Report r) throws SQLException {
        Connection connection= getConnection();
        PreparedStatement ps = connection.prepareStatement(ADD_REPORT);

        ps.setString(1,String.valueOf(r.getPostId()));
        ps.setString(2,String.valueOf(r.getCommentId()));
        ps.setString(3,r.getReason());
        ps.setString(4,r.getContent());
        ps.execute();
    }
}
