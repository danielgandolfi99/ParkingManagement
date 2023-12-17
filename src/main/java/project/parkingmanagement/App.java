package project.parkingmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage stage;

    private static Scene about;

    private static Scene closeParking;

    private static Scene home;

    private static Scene login;

    private static Scene logout;

    private static Scene table;

    private static Scene registerNewVehicle;

    private static String username;

    private static String plate;

    private static double hourlyRate = 7.00;

    private static int totalVacancies = 50;

    private static int occupation;

    private static int totalRegisters;

    private static double dailyCollection;



    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        primaryStage.setTitle("Sistema de Estacionamento");

        FXMLLoader login_FXML = new FXMLLoader(App.class.getResource("login.fxml"));
        login = new Scene(login_FXML.load());

        primaryStage.setScene(login);
        primaryStage.show();
    }

    public static void setHourlyRate(double hourlyRate) {
        App.hourlyRate = hourlyRate;
    }

    public static double getHourlyRate() {
        return hourlyRate;
    }



    public static void setTotalVacancies(int totalVacancies) {
        App.totalVacancies = totalVacancies;
    }

    public static int getTotalVacancies() {
        return totalVacancies;
    }

    public static int getOccupation() {
        return occupation;
    }

    public static void setOccupation(int occupation) {
        App.occupation = occupation;
    }

    public static int getTotalRegisters() {
        return totalRegisters;
    }

    public static void setTotalRegisters(int totalRegisters) {
        App.totalRegisters = totalRegisters;
    }

    public static double getDailyCollection() {
        return dailyCollection;
    }

    public static void setDailyCollection(double dailyCollection) {
        App.dailyCollection = dailyCollection;
    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }

    public static String getUsername() {
        return username;
    }

    public static void setPlate(String newPlate) {
        plate = newPlate;
    }

    public static String getPlate() {
        return plate;
    }

    public static void changeScreen(String screen) throws IOException {
        switch (screen){
            case "about":
                FXMLLoader about_FXML = new FXMLLoader(App.class.getResource("about.fxml"));
                about = new Scene(about_FXML.load());
                stage.setScene(about);
                stage.show();
                break;
            case "closeParking":
                FXMLLoader closeParking_FXML = new FXMLLoader(App.class.getResource("closeParking.fxml"));
                closeParking = new Scene(closeParking_FXML.load());
                stage.setScene(closeParking);
                stage.show();
                break;
            case "home":
                FXMLLoader home_FXML = new FXMLLoader(App.class.getResource("home.fxml"));
                home = new Scene(home_FXML.load());
                stage.setScene(home);
                stage.show();
                break;
            case "login":
                stage.setScene(login);
                stage.show();
                break;
            case "logout":
                FXMLLoader logout_FXML = new FXMLLoader(App.class.getResource("logout.fxml"));
                logout = new Scene(logout_FXML.load());
                stage.setScene(logout);
                stage.show();
                break;
            case "table":
                FXMLLoader table_FXML = new FXMLLoader(App.class.getResource("table.fxml"));
                table = new Scene(table_FXML.load());
                stage.setScene(table);
                stage.show();
                break;
            case "registerNewVehicle":
                FXMLLoader registerNewVehicle_FXML = new FXMLLoader(App.class.getResource("registerNewVehicle.fxml"));
                registerNewVehicle = new Scene(registerNewVehicle_FXML.load());
                stage.setScene(registerNewVehicle);
                stage.show();
                break;
            default:
                break;
        }
    }

    public static Stage getStage(){
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}