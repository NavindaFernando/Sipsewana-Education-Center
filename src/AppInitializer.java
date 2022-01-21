import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

        @Override
        public void start(Stage primaryStage) throws IOException {
       /* URL resource = getClass().getResource("views/Splash_Screen.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        scene.getStylesheets().add("styles/style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gym Management System ( V-1.0.0 )");
        primaryStage.show();
*/
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("views/Splash_Screen.fxml"))));
            primaryStage.isAlwaysOnTop();
            primaryStage.show();
        }
    }
