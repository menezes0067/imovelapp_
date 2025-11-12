package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String serverName = "localhost";
        String database ="imovelapp_";
        String url = "jdbc:mysql://" + serverName + "/" + database;
        String username = "root";
        String password = "urubu100";
        String DriverName = "com.mysql.jdbc.Driver";

        Class.forName(DriverName);

        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
