package com.tornado4651.simple_email.Dao;

import com.tornado4651.simple_email.Entity.Email;
import com.tornado4651.simple_email.util.DButil;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.List;

public class EmailDao {

    public boolean recEmail(String receiver, String subject, String content) {
        DButil dbu = new DButil();

        if (dbu.connectDb("EmailSys")) {
            try {
                Date date = new Date();
                Timestamp datetime = new Timestamp(date.getTime());
                String sql = "INSERT INTO email(datetime,receiver,subject,content) VALUES (?,?,?,?)";
                Connection conn = dbu.conn;
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setObject(1,datetime);
                ps.setObject(2,receiver);
                ps.setObject(3,subject);
                ps.setObject(4,content);
                ps.executeUpdate();
                dbu.closeDB(conn);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        else{
            System.out.println("连接数据库失败");
            return false;
        }
    }

    public List<Email> getEmail() {
        Email email = null;
        DButil dbu = new DButil();
        List<Email> datalist = new ArrayList<>();
        if (dbu.connectDb("EmailSys")){
            try {
                String sql = "SELECT * FROM email";
                Connection conn = dbu.conn;
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    email = new Email();
                    email.setId(rs.getInt("id"));
                    email.setDatetime(rs.getTimestamp("datetime"));
                    email.setReceiver(rs.getString("receiver"));
                    email.setSubject(rs.getString("subject"));
                    email.setContent(rs.getString("content"));
                    datalist.add(email);
                }
                dbu.closeDB(conn);
                return datalist;

            } catch (SQLException e) {
                e.printStackTrace();
                return datalist;
            }
        }
        else{
            System.out.println("连接数据库失败");
            return datalist;
        }
    }

}
