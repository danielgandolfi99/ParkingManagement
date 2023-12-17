package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;

public class DAO_new_vehicle {
    public static boolean insertNewRegister(String plate, String manufacturer, String model, String color,
                                         String year, Timestamp date) {
        Connection connection = ConnectionDB.getConnection();
        String procedureCall = "{CALL register_new_vehicle(?, ?, ?, ?, ?, ?)}";
        try (CallableStatement statement = connection.prepareCall(procedureCall)) {
            statement.setString(1, plate);
            statement.setString(2, manufacturer);
            statement.setString(3, model);
            statement.setString(4, color);
            statement.setString(5, year);
            statement.setTimestamp(6, date);
            statement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }

}
