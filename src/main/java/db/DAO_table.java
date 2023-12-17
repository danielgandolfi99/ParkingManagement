package db;

import project.parkingmanagement.Classes.TimesRegister;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DAO_table {
    public static List selectAllRegisters() {
        Connection connection = ConnectionDB.getConnection();
        String procedureCall = "{CALL select_all_registers()}";
        List<TimesRegister> dataRegisters = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall(procedureCall)) {
            if (statement.execute()) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int vehicle_id = resultSet.getInt("vehicle_id");
                    String plate = resultSet.getString("plate");
                    String manufacturer = resultSet.getString("manufacturer");
                    String model = resultSet.getString("model");
                    String color = resultSet.getString("color");
                    String year = resultSet.getString("year");
                    int time_id = resultSet.getInt("time_id");
                    Timestamp entry_time = resultSet.getTimestamp("entry_time");
                    Timestamp exit_time = resultSet.getTimestamp("exit_time");
                    TimesRegister timesRegisterObj = new TimesRegister(vehicle_id, plate, manufacturer, model, color,
                            year, time_id, vehicle_id, entry_time, exit_time);
                    dataRegisters.add(timesRegisterObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO: " + e.getMessage());
        }
        return dataRegisters;
    }
}
