package controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginFormController {

    public AnchorPane loginContext;
    public TextField txtPassword;

    public void openDashBoard(MouseEvent mouseEvent) throws IOException {
        /*Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/Dashboard_Form.fxml"))));*/

    if (txtPassword.getText().equals("0000")) {
        Stage window = (Stage) loginContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/Dashboard_Form.fxml"))));
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Check Your Password !", ButtonType.CLOSE).show();
        }
    }
}
