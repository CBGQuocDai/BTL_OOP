package com.example.demo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.Model.Report;

@Component
public class ReportDAO {
    private String jdbcURL = "jdbc:mysql://mysql-4bc7aa-quocdaicbg001-d224.c.aivencloud.com:16253/defaultdb";
    private String jdbcUsername = "avnadmin";
    private String jdbcPassword = "AVNS_jfijrHh9AlwIpwNz30Z";

    private static final String GET_ALL_REPORT= "SELECT * FROM report";
    private final static String ADD_REPORT ="INSERT INTO report(reportId,postId,commentId,reason,content,time) VALUES(?,?,?,?,?,NOW())";
    private static final String GET_REPORT_BY_ID= "SELECT * FROM report WHERE reportId = ?";
    private final String DELETE_REPORT = "DELETE FROM report WHERE reportId = ?";

    public void deleteReportById (String id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_REPORT);

        ps.setString(1 , id);

        ps.executeUpdate();
    }

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

    public List<Report> getAllReport () {
        List<Report> reports = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_REPORT);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Report report = new Report();
                report.setReportId(rs.getInt("reportId"));
                report.setPostId(rs.getInt("postId"));
                report.setCommentId(rs.getInt("commentId"));
                report.setContent(rs.getString("content"));
                report.setReason(rs.getString("reason"));
                report.setTime(Timestamp.valueOf(rs.getString("time")));
                reports.add(report);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return reports;
    }

    public void addReport(Report r) throws SQLException {
        Connection connection= getConnection();
        PreparedStatement ps = connection.prepareStatement(ADD_REPORT);
        ps.setString(1,String.valueOf(r.getReportId()));
        ps.setString(2,String.valueOf(r.getPostId()));
        ps.setString(3,String.valueOf(r.getCommentId()));
        ps.setString(4,r.getReason());
        ps.setString(5,r.getContent());
        ps.execute();
    }

    public Report selectReportById(int id){
        Report report= new Report();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_REPORT_BY_ID);
            ps.setString(1,String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                report.setReportId(rs.getInt("reportId"));
                report.setPostId(rs.getInt("postId"));
                report.setCommentId(rs.getInt("commentId"));
                report.setContent(rs.getString("content"));
                report.setReason(rs.getString("reason"));
                report.setTime(Timestamp.valueOf(rs.getString("time")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

}
