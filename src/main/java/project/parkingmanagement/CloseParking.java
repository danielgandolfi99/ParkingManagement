package project.parkingmanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CloseParking implements Initializable {

    @FXML
    private Text date;

    @FXML
    private Text carNumbers;

    @FXML
    private Text amountCollected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp currentDate = Timestamp.valueOf(currentDateTime);

        setDate("Data da consulta: " + sdf.format(currentDate));
        setCarNumbers("Número de carros: " + App.getTotalRegisters());
        setAmountCollected("Valor arrecadado: "  + " R$ " + App.getDailyCollection() );
    }

    public void setDate(String newDate) {
        date.setText(newDate);
    }

    public void setCarNumbers(String numbersCar) {
        carNumbers.setText(numbersCar);
    }

    public void setAmountCollected(String value) {
        amountCollected.setText(value);
    }

    @FXML
    void onReturn(ActionEvent event) throws IOException {
        App.getStage().close();
        App.changeScreen("home");
    }

    @FXML
    void onExit(ActionEvent event) {
        Platform.exit();
    }

}
