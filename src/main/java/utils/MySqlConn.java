package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConn {

    private static Connection conn = null;
    
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_universitaria";
    private static final String username = "root";
    private static final String password = "1234";

    private MySqlConn() {
    }

    public static MySqlConn getInstance() {
        return MySqlConnHolder.INSTANCE;
    }

    public Connection connectDb() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, username, password);
                System.out.println("Database connected: " + (conn != null));
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Database connection error: " + e.getMessage());
            }
        }
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed.");
                conn = null;
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    private static class MySqlConnHolder {
        private static final MySqlConn INSTANCE = new MySqlConn();
    }
}
