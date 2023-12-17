package project.parkingmanagement;

import db.DAO_new_vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class RegisterNewVehicle implements Initializable {
    @FXML
    private TextField color;

    @FXML
    private TextField year;

    @FXML
    private TextField plate;

    @FXML
    private TextField model;

    @FXML
    private TextField manufacturer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPlate(App.getPlate());
        setupYearFilter();
        plate.setEditable(false);
    }

    private void setupYearFilter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            String regex = "^\\d{0,4}$";

            if (newText.matches(regex) || newText.isEmpty()) {
                return change;
            } else {
                return null;
            }
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        year.setTextFormatter(textFormatter);
    }

        public void setPlate(String newPlate) {
        plate.setText(newPlate);
    }

    @FXML
    void onConfirm(ActionEvent event) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp currentDate = Timestamp.valueOf(currentDateTime);
        Boolean success = DAO_new_vehicle.insertNewRegister(plate.getText(), manufacturer.getText(), model.getText(), color.getText(),
                year.getText(), currentDate);
        if (success){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Entrada de Veículo Registrada");
            alert.setHeaderText("Placa: " + plate.getText() + "\n"
                    + "Horario de Entrada: " + sdf.format(currentDate));
            alert.setContentText(null);
            alert.showAndWait();
            App.getStage().close();
            App.changeScreen("home");
        }
    }

    @FXML
    void onReturn(ActionEvent event) throws IOException {
        App.getStage().close();
        App.changeScreen("home");
    }

}
