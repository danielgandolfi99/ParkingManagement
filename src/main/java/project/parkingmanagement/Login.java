package project.parkingmanagement;

import db.DAO_login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    public TextField login;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().length() <= 6) {
                return change;
            } else {
                return null;
            }
        });
        password.setTextFormatter(textFormatter);
    }

    @FXML
    void onConfirm(ActionEvent event) throws IOException {
        if (login.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Campos vazios");
            alert.setHeaderText("Por favor, preencha todos os campos.");
            alert.setContentText("Certifique-se de inserir seu login e senha.");
            alert.showAndWait();
        } else {
            App.setUsername(login.getText());
            Boolean checkLogin = DAO_login.insertUser(login.getText(), password.getText());
            if (checkLogin) {
                App.getStage().close();
                App.changeScreen("home");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Senha incorreta");
                alert.setHeaderText("A senha que você digitou para o usuario esta incorreta.");
                alert.setContentText("Tente realizar o login novamente!");
                alert.showAndWait();
            }
        }
    }
}
