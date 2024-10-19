package com.example.demo.DAO;

import com.example.demo.Model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class UserDAO {
    private String jdbcURL = "jdbc:mysql://mysql-4bc7aa-quocdaicbg001-d224.c.aivencloud.com:16253/defaultdb";
    private String jdbcUsername = "avnadmin";
    private String jdbcPassword = "AVNS_jfijrHh9AlwIpwNz30Z";

    private final String GET_USER_BY_USERNAME="SELECT * FROM user WHERE username =? ";
    private final String GET_USER_BY_USERID="SELECT * FROM user WHERE userId =? ";
    private final String GET_ALL_USER = "SELECT * FROM user";
    private final String DELETE_USER = "DELETE FROM user WHERE userId = ?";

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

    public void deleteUserById (String id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_USER);

        ps.setString(1 , id);

        ps.executeUpdate();
    }

    public ArrayList<User> getAll () throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps =connection.prepareStatement(GET_ALL_USER);
        ArrayList<User> users = new ArrayList<>();
        ResultSet result = ps.executeQuery();

        if (result != null) {
            while(result.next()){
                User user = new User();
                user.setUserId(result.getInt("userId"));
                user.setUsername(result.getString("userName"));
                user.setPassword(result.getString("password"));
                user.setGender(result.getInt("gender"));
                user.setEmail(result.getString("email"));
                user.setAvatar(result.getString("avatar"));
                user.setRole(result.getString("role"));

                users.add(user);
            }
        }
        return users;
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
