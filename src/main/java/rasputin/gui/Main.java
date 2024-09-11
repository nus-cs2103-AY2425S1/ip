package rasputin.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import rasputin.Rasputin;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Rasputin rasputin = new Rasputin("./data/rasputin.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRasputin(rasputin);  // inject the Rasputin instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
