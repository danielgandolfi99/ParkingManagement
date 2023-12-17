package project.parkingmanagement;

import db.DAO_table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.parkingmanagement.Classes.TimesRegister;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class Table implements Initializable {
    @FXML
    private TableView<TimesRegister> table;

    @FXML
    private TableColumn<TimesRegister, Timestamp> exit_time;

    @FXML
    private TableColumn<TimesRegister, Timestamp> entry_time;

    @FXML
    private TableColumn<TimesRegister, String> color;

    @FXML
    private TableColumn<TimesRegister, String> year;

    @FXML
    private TableColumn<TimesRegister, String> plate;

    @FXML
    private TableColumn<TimesRegister, String> model;

    @FXML
    private TableColumn<TimesRegister, Integer> vehicle_id;

    @FXML
    private TableColumn<TimesRegister, String> manufacturer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<TimesRegister> data = FXCollections.observableArrayList();
        data.addAll(DAO_table.selectAllRegisters());

        vehicle_id.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        exit_time.setCellValueFactory(new PropertyValueFactory<>("exit_time"));
        entry_time.setCellValueFactory(new PropertyValueFactory<>("entry_time"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        plate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        table.setItems(data);
    }

    @FXML
    void onReturn(ActionEvent event) throws IOException {
        App.getStage().close();
        App.changeScreen("home");
    }

}
