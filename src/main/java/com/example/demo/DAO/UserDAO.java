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
    private String jdbcURL = "jdbc:mysql://localhost:3306/BlogDB";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345";

    private final String GET_USER_BY_USERNAME = "SELECT * FROM user WHERE username =? ";
    private final String GET_USER_BY_USERID = "SELECT * FROM user WHERE userId =? ";
    private final String GET_ALL_USER = "SELECT * FROM user";
    private final String DELETE_USER = "DELETE FROM user WHERE userId = ?";
    private final String UPDATE_USER = "UPDATE user SET username = ?, phone = ?, gender = ?, email = ? WHERE userId = ?";
    private final String UPDATE_AVATAR = "UPDATE user SET avatar = ? WHERE userId = ?";

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void deleteUserById(String id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_USER);

        ps.setString(1, id);

        ps.executeUpdate();
    }

    public ArrayList<User> getAll() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(GET_ALL_USER);
        ArrayList<User> users = new ArrayList<>();
        ResultSet result = ps.executeQuery();

        if (result != null) {
            while (result.next()) {
                User user = new User();
                user.setUserId(result.getInt("userId"));
                user.setUsername(result.getString("userName"));
                user.setPassword(result.getString("password"));
                user.setGender(result.getString("gender"));
                user.setEmail(result.getString("email"));
                user.setAvatar(result.getString("avatar"));
                user.setRole(result.getString("role"));

                users.add(user);
            }
        }
        return users;
    }

    public User getUserByUsername(String name) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(GET_USER_BY_USERNAME);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        if (rs.next()) {
            user.setUserId(rs.getInt("userId"));
            user.setUsername(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setAvatar(rs.getString("avatar"));
            user.setRole(rs.getString("role"));
        }
        return user;
    }

    public User getUserByUserId(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(GET_USER_BY_USERID);
        ps.setString(1, String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        User user = new User();
        if (rs.next()) {
            user.setUserId(rs.getInt("userId"));
            user.setUsername(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setAvatar(rs.getString("avatar"));
            user.setRole(rs.getString("role"));
        }
        return user;
    }

    public void updateUser(User user) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getUserId());
            ps.executeUpdate();
        }
    }
    private static String CHANGE_PASSWORD="UPDATE user SET password =? WHERE userId=?";
    public void updatePassword(User user){
        try {
            Connection connection = getConnection();
            PreparedStatement ps =connection.prepareStatement(CHANGE_PASSWORD);
            ps.setString(1,user.getPassword());
            ps.setInt(2,user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
