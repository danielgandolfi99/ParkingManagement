package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking_management","root","daniel99");
            return conn;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
