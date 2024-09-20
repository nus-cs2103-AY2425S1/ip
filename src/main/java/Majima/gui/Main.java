package Majima.gui;

import Majima.Majima;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */



public class Main extends Application {

    public static final String FILE_PATH = "data/Majima.txt";

    private Majima majimabot = new Majima(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Majimabot");

            fxmlLoader.<MainWindow>getController().setMajimabot(majimabot);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
