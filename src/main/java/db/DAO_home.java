package db;

import project.parkingmanagement.Classes.TimesRegister;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO_home {

    public static List selectDailyData() {
        Connection connection = ConnectionDB.getConnection();
        String procedureCall = "{CALL select_all_daily_times_register()}";
        List<TimesRegister> timesRegisters = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall(procedureCall)) {
            if (statement.execute()) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int time_id = resultSet.getInt("time_id");
                    int vehicle_id = resultSet.getInt("vehicle_id");
                    Timestamp entry_time = resultSet.getTimestamp("entry_time");
                    Timestamp exit_time = resultSet.getTimestamp("exit_time");
                    TimesRegister timesRegisterObj = new TimesRegister(time_id, vehicle_id, entry_time, exit_time);
                    timesRegisters.add(timesRegisterObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO: " + e.getMessage());
        }
        return timesRegisters;
    }

    public static Timestamp checkPlate(String plate) {
        Connection connection = ConnectionDB.getConnection();
        String procedureCall = "{CALL check_plate(?, ?)}";
        Timestamp entryTime = null;
        try (CallableStatement statement = connection.prepareCall(procedureCall)) {
            statement.setString(1, plate);
            statement.registerOutParameter(2, Types.TIMESTAMP);
            statement.execute();
            entryTime = statement.getTimestamp(2);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO: " + e.getMessage());
        }
        return entryTime;
    }

    public static void updateExitTime(String plate, Timestamp currentDate) {
        Connection connection = ConnectionDB.getConnection();
        String procedureCall = "{CALL update_exit_time(?, ?)}";
        try (CallableStatement statement = connection.prepareCall(procedureCall)) {
            statement.setString(1, plate);
            statement.setTimestamp(2, currentDate);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static TimesRegister selectDataPlate(String plate) {
        Connection connection = ConnectionDB.getConnection();
        String procedureCall = "{CALL select_data_plate(?)}";
        TimesRegister timesRegister = null;
        try (CallableStatement statement = connection.prepareCall(procedureCall)) {
            statement.setString(1, plate);
            if (statement.execute()) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int time_id = resultSet.getInt("time_id");
                    int vehicle_id = resultSet.getInt("vehicle_id");
                    Timestamp entry_time = resultSet.getTimestamp("entry_time");
                    Timestamp exit_time = resultSet.getTimestamp("exit_time");
                    TimesRegister timesRegisterObj = new TimesRegister(time_id, vehicle_id, entry_time, exit_time);
                    timesRegister = timesRegisterObj;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO: " + e.getMessage());
        }
        return timesRegister;
    }

}
