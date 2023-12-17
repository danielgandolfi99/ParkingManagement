package project.parkingmanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Logout {

    @FXML
    void onNot(ActionEvent event) throws IOException {
        App.getStage().close();
        App.changeScreen("home");
    }

    @FXML
    void OnYes(ActionEvent event) {
        Platform.exit();
    }
}
