package hoshi.gui;


import java.io.IOException;

import hoshi.Hoshi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

            // inject the hoshi instance
            fxmlLoader.<MainWindow>getController().setHoshi(hoshi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
