package project.parkingmanagement;

import db.DAO_home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
import project.parkingmanagement.Classes.TimesRegister;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class Home implements Initializable {
    @FXML
    private Text occupation;

    @FXML
    private Text dailyCollection;

    @FXML
    private Text user;

    @FXML
    private TextField plate;

    @FXML
    private Text tariff;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupPlateFilter();
        loadScreenSummary();
    }

    private void setupPlateFilter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            String regex = "^[A-Z]{0,3}(-\\d{0,4})?$";

            if (newText.matches(regex) || newText.isEmpty()) {
                return change;
            } else {
                return null;
            }
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        plate.setTextFormatter(textFormatter);

        plate.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 3 && !oldValue.equals(newValue)) {
                plate.setText(newValue + "-");
            }
        });
    }

    private void loadScreenSummary(){
        List<TimesRegister> timesRegisters = DAO_home.selectDailyData();
        long totalHours = 0;
        int totalOccupation = 0;
        for (TimesRegister timesRegister : timesRegisters) {
            Timestamp entry_time = timesRegister.getNoFormattingEntryTime();
            Timestamp exit_time = timesRegister.getNoFormattingExitTime();
            if(exit_time != null){
                Duration duration = Duration.between(entry_time.toInstant(), exit_time.toInstant());
                if(duration.toHours() == 0){
                    totalHours += 1;
                } else if (duration.toMinutes() > 0) {
                    totalHours += duration.toHours() + 1;
                } else {
                    totalHours += duration.toHours();
                }
            } else {
                totalOccupation += 1;
            }
        }

        App.setOccupation(totalOccupation * 100 / App.getTotalVacancies());
        App.setDailyCollection(totalHours * App.getHourlyRate());

        App.setTotalRegisters(timesRegisters.size());

        String username = App.getUsername();

        setOccupation("Taxa de Ocupação: " + App.getOccupation() + "%");
        setUser("Usuário: " + username);
        setTariff("Valor da Tarifa: R$ " + App.getHourlyRate() + " / hora");
        setDailyCollection("Receita do dia: " + " R$ " + App.getDailyCollection());
    }




    public void setOccupation(String newOccupation) {
        occupation.setText(newOccupation);
    }

    public void setDailyCollection(String newDailyCollection) {
        dailyCollection.setText(newDailyCollection);
    }

    public void setUser(String username) {
        user.setText(username);
    }

    public void setTariff(String newTarrif) {
        tariff.setText(newTarrif);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void onSend(ActionEvent event) throws IOException {
        String plateText = plate.getText();
        if (!plateText.matches("^[A-Z]{3}-\\d{4}$")) {
            showAlert("Formato de placa inválido!", "Por favor, insira uma placa válida no formato XXX-1111.");
        } else {
            Timestamp entryTime = DAO_home.checkPlate(plate.getText());
            if (entryTime != null) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                Timestamp currentDate = Timestamp.valueOf(currentDateTime);
                long totalHours = 0;

                DAO_home.updateExitTime(plate.getText(), currentDate);

                TimesRegister timesRegister = DAO_home.selectDataPlate(plate.getText());

                Duration duration = Duration.between(entryTime.toInstant(), currentDate.toInstant());
                if(duration.toHours() == 0){
                    totalHours += 1;
                } else if (duration.toMinutes() > 0) {
                    totalHours += duration.toHours() + 1;
                } else {
                    totalHours += duration.toHours();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Saida de Veiculo Registrada");
                alert.setHeaderText("Placa: " + plate.getText() + "\n"
                        + "Horario de Entrada: " + timesRegister.getEntry_time() + "\n"
                        + "Horario de Saida: " + timesRegister.getExit_time() + "\n"
                        + "Valor a ser Pago: " + totalHours * App.getHourlyRate());
                alert.setContentText(null);
                alert.showAndWait();
                App.getStage().close();
                App.changeScreen("home");
            } else {
                App.setPlate(plate.getText());
                App.getStage().close();
                App.changeScreen("registerNewVehicle");
            }
        }

    }

    @FXML
    void onCloseParking(ActionEvent event) throws IOException {
        App.getStage().close();
        App.changeScreen("closeParking");
    }

    @FXML
    void onRecodsTable(ActionEvent event) throws IOException {
        App.getStage().close();
        App.changeScreen("table");
    }

    @FXML
    void onLogout(ActionEvent event) throws IOException {
        App.getStage().close();
        App.changeScreen("logout");
    }

    @FXML
    void onAbout(ActionEvent event) throws IOException {
        App.getStage().close();
        App.changeScreen("about");
    }

}
