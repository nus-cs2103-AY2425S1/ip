package gui;

import java.io.IOException;

import dynamike.Dynamike;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dynamike using FXML.
 */
public class Main extends Application {

    private Dynamike dynamike = new Dynamike();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Dynamike");
            fxmlLoader.<MainWindow>getController().setDynamike(dynamike);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
