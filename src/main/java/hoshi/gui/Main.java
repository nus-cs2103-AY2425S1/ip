package hoshi.gui;


import java.io.IOException;

import hoshi.Hoshi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Hoshi using FXML.
 */
public class Main extends Application {

    private final Hoshi hoshi = new Hoshi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // customize stage
            stage.setTitle("Hoshi - Your Task Assistant"); // Setting window title
            stage.setMinHeight(220);
            stage.setMinWidth(450);

            // image for app icon
            Image image = new Image("/images/Hoshi.JPG");
            stage.getIcons().add(image);

            // center the window on the screen when appearing
            stage.centerOnScreen();

            // inject the hoshi instance
            fxmlLoader.<MainWindow>getController().setHoshi(hoshi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
