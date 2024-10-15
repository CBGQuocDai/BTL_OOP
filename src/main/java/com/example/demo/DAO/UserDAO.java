package com.example.demo.DAO;

import com.example.demo.Model.User;

import java.sql.*;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/BlogDB";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345";

    private final String GET_USER_BY_USERNAME="SELECT * FROM user WHERE username =? ";
    private final String GET_USER_BY_USERID="SELECT * FROM user WHERE userId =? ";
    public UserDAO(){}
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
    public User getUserByUsername(String name) throws SQLException {
        Connection connection =getConnection();
        PreparedStatement ps =connection.prepareStatement(GET_USER_BY_USERNAME);
        ps.setString(1,name);
        ResultSet rs= ps.executeQuery();
        User user =new User();
        if(rs.next()){
            user.setUserId(rs.getInt("userId"));
            user.setUsername(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getInt("gender"));
            user.setEmail(rs.getString("email"));
            user.setAvatar(rs.getString("avatar"));
            user.setRole(rs.getString("role"));
        }
        return user;
    }
    public User getUserByUserId(int id) throws SQLException {
        Connection connection =getConnection();
        PreparedStatement ps =connection.prepareStatement(GET_USER_BY_USERID);
        ps.setString(1,String.valueOf(id));
        ResultSet rs= ps.executeQuery();
        User user =new User();
        if(rs.next()){
            user.setUserId(rs.getInt("userId"));
            user.setUsername(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getInt("gender"));
            user.setEmail(rs.getString("email"));
            user.setAvatar(rs.getString("avatar"));
            user.setRole(rs.getString("role"));
        }
        return user;
    }
}
