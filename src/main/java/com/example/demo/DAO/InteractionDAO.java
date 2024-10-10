package com.example.demo.DAO;

import com.example.demo.Model.Interaction;

import java.sql.*;

public class InteractionDAO {
    private String jdbcURL = "jdbc:mysql://mysql-4bc7aa-quocdaicbg001-d224.c.aivencloud.com:16253/defaultdb";
    private String jdbcUsername = "avnadmin";
    private String jdbcPassword = "AVNS_jfijrHh9AlwIpwNz30Z";

    private static final String GET_VOTE_UP="SELECT count(DISTINCT interactionId) FROM interaction WHERE postId= ? AND type ='up'";
    private static final String GET_VOTE_DOWN="SELECT count(DISTINCT interactionId) FROM interaction WHERE postId= ? AND type ='down'";
    private static final String GET_INTERACTION="SELECT * FROM interaction WHERE postId=? AND userId= ? AND (type =? OR type =?)";
    private static final String DELETE_INTERACTION ="DELETE FROM interaction WHERE interactionId= ?";
    private static final String UPDATE_VOTE = "UPDATE interaction SET time = NOW() ,type =? WHERE interactionId=?";
    private static final String ADD_INTERACTION = "INSERT INTO interaction (interactionId,userId,postId,type,time) VALUES (?,?,?,?,NOW())";

    public InteractionDAO(){}
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
    // tương tác với vote
    public int getNumVote(String id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement voteUp = connection.prepareStatement(GET_VOTE_UP);
        voteUp.setString(1,String.valueOf(id));
        ResultSet rs=voteUp.executeQuery();
        int cntVoteUp=0,cntVoteDown=0;
        if(rs.next()) {
            cntVoteUp = rs.getInt("count(DISTINCT interactionId)");
        }
        PreparedStatement voteDown = connection.prepareStatement(GET_VOTE_DOWN);
        voteDown.setString(1,String.valueOf(id));
        ResultSet rs1= voteDown.executeQuery();
        if(rs1.next()){
            cntVoteDown= rs1.getInt("count(DISTINCT interactionId)");
        }
        return cntVoteUp - cntVoteDown;
    }
    private Interaction getInteraction(int postId, int userId,String type1,String type2) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps= connection.prepareStatement(GET_INTERACTION);
        ps.setString(1,String.valueOf(postId));
        ps.setString(2,String.valueOf(userId));
        ps.setString(3,type1);
        ps.setString(4,type2);
        ResultSet rs = ps.executeQuery();

        Interaction vote =new Interaction(); // chưa vote

        if(rs.next()){
            vote.setInteractionId(rs.getInt("interactionId"));
            vote.setUserId(rs.getInt("userId"));
            vote.setPostId(rs.getInt("postId"));
            vote.setType(rs.getString("type"));
            vote.setTime(rs.getTimestamp("time"));
        }
        return vote;
    }
    public int getUserVote(int postId, int userId) throws SQLException {
        Interaction vote = getInteraction(postId,userId,"up","down");
        if(vote.getType() == null) return 0;
        int userVote=0;
        if(vote.getType().equals("up")){
            userVote=1;
        }
        else userVote=-1;
        return userVote;
    }
    public void setInteraction(int postId, int userId, String type) throws SQLException {
        if(type.equals("bookmark")){
            Interaction bookmark = getInteraction(postId,userId,"bookmark","bookmark");
            Connection connection =getConnection();
            if(bookmark.getType()== null){
                PreparedStatement addBookmark= connection.prepareStatement(ADD_INTERACTION);
                addBookmark.setString(1, String.valueOf(bookmark.getInteractionId()));
                addBookmark.setString(2,String.valueOf(userId));
                addBookmark.setString(3,String.valueOf(postId));
                addBookmark.setString(4,type);
                addBookmark.execute();
            }
            else{
                PreparedStatement deleteBookmark=connection.prepareStatement(DELETE_INTERACTION);
                deleteBookmark.setString(1, String.valueOf(bookmark.getInteractionId()));
                deleteBookmark.execute();
            }
        }
        else{
            Interaction vote= getInteraction(postId,userId,"up","down");
            Connection connection =getConnection();
            if(vote.getType()== null){
                PreparedStatement addVote = connection.prepareStatement(ADD_INTERACTION);
                addVote.setString(1, String.valueOf(vote.getInteractionId()));
                addVote.setString(2,String.valueOf(userId));
                addVote.setString(3,String.valueOf(postId));
                addVote.setString(4,type);
                addVote.execute();
            }
            else {
                if(type.equals(vote.getType())) {
                    PreparedStatement delete = connection.prepareStatement(DELETE_INTERACTION);
                    delete.setString(1, String.valueOf(vote.getInteractionId()));
                    delete.execute();
                }
                else {
                    PreparedStatement update = connection.prepareStatement(UPDATE_VOTE);
                    update.setString(1,type);
                    update.setString(2,String.valueOf(vote.getInteractionId()));
                    update.execute();
                }
            }
        }
    }
    // tương tác bookmark
    public int getStateBookmark(int postId,int userId) throws SQLException {
        Interaction bookmark = getInteraction(postId,userId,"bookmark","bookmark");
        if(bookmark.getType() == null) return 0;
        else return 1;
    }

}
