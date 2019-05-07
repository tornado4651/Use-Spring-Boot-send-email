package com.tornado4651.simple_email.Dao;

import com.tornado4651.simple_email.Entity.User;
import com.tornado4651.simple_email.util.DButil;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class UserDao {

    public User get_ByUsername(String username) {
        User u = null;
        DButil dbu = new DButil();
        if (dbu.connectDb("EmailSys")) {
            try {
                String sql = "SELECT * FROM user where username=?";
                Connection conn = dbu.conn;
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setObject(1,username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
//                    System.out.println(rs.getString("username"));

                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
//                    System.out.println(rs.getInt(1)+"---"+rs.getString(2)+"---"+rs.getString(3));
                }
                dbu.closeDB(conn);
                return u;

            } catch (SQLException e) {
                e.printStackTrace();
                return u;
            }
        }
        else{
            System.out.println("连接数据库失败");
            return u;
        }
    }

    public boolean registerUser(String username, String password) {
        User u = null;
        DButil dbu = new DButil();
        if (dbu.connectDb("EmailSys")) {
            try {
                String sql = "INSERT INTO user(username,password) VALUES (?,?)";
                Connection conn = dbu.conn;
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setObject(1,username);
                ps.setObject(2,password);
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

}
