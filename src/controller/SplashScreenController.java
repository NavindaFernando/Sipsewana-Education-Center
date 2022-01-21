package controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreenController {
    public JFXProgressBar pgb;
    public AnchorPane splashContext;

    public void initialize(){
        pgb.setProgress(0);
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            if (pgb.getProgress() <= 1) {
                pgb.setProgress(pgb.getProgress() + 0.01);
            }
        }));
        tl.setRate(3);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.playFromStart();
        pgb.progressProperty().addListener((observable, oldValue, newValue) -> {

            try {
                if (newValue.intValue() == 1) {
                    tl.stop();
                    Stage window = (Stage) splashContext.getScene().getWindow();
                    window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/Login_Form.fxml"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
}
