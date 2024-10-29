package com.example.demo.DAO;

import com.example.demo.Model.Notification;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationDAO {
    private String jdbcURL = "jdbc:mysql://mysql-4bc7aa-quocdaicbg001-d224.c.aivencloud.com:16253/defaultdb";
    private String jdbcUsername = "avnadmin";
    private String jdbcPassword = "AVNS_jfijrHh9AlwIpwNz30Z";

    private static final String SAVE_NOTICE = "INSERT INTO notifications (message, postId, userId, time) VALUES (?, ?, ?, NOW())";
    private static final String GET_ALL_NOTICE="SELECT * FROM notifications WHERE userId = ? ORDER BY id DESC";
    public NotificationDAO(){}
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
    public List<Notification> findByUserId(int userId){
        List<Notification> notices = new ArrayList<>();
        try {
            Connection connection=getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_NOTICE);
            ps.setString(1,String.valueOf(userId));
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Notification x= new Notification();
                x.setId(rs.getInt("id"));
                x.setMessage(rs.getString("message"));
                x.setPostId(rs.getInt("postId"));
                x.setUserId(rs.getInt("userId"));
                x.setTime(rs.getTimestamp("time"));
                notices.add(x);
            }
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return notices;
    }
    public void save(Notification notice){
        try{
            System.out.println(notice.getMessage());
            Connection connection =getConnection();
            PreparedStatement ps =connection.prepareStatement(SAVE_NOTICE);
            ps.setString(1, notice.getMessage());
            ps.setInt(2, notice.getPostId());
            ps.setInt(3, notice.getUserId());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
