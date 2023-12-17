package project.parkingmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class About {

    @FXML
    void onReturn(ActionEvent event) throws IOException {
        App.getStage().close();
        App.changeScreen("home");
    }
}
