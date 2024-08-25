package hoodini;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class to access chatbot
 */
public class Main extends Application {


    private Hoodini hoodini = new Hoodini();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/HoodiniMainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<HoodiniMainWindow>getController().setHoodini(hoodini);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
