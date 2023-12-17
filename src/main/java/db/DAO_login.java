package db;

import java.sql.*;

public class DAO_login {
    public static boolean insertUser(String login, String password) {
        Connection connection = ConnectionDB.getConnection();
        Boolean checkLogin = false;
        String procedureCall = "{CALL login_user(?, ?, ?)}";
        try (CallableStatement statement = connection.prepareCall(procedureCall)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.registerOutParameter(3, Types.BOOLEAN);
            statement.execute();
            checkLogin = statement.getBoolean(3);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERRO: " + e.getMessage());
        }
        return checkLogin;
    }
}
