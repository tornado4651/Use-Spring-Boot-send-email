package com.tornado4651.simple_email.util;

import java.sql.*;

public class DButil {

    private String oracleDriver = "oracle.jdbc.driver.Driver";
    private String mysqlDriver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/";
    private String user="root";
    private String password="18347176536=Ljf";
    public Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    Statement stmt = null;

    public boolean connectDb(String databaseName){
        try{
            //
            Class.forName(mysqlDriver);
            this.url += databaseName;
            conn = DriverManager.getConnection(url, user, password);
            return true;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeDB(Connection connection) {
        try{
            if(connection != null){
                connection.close();

            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
