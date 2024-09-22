package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import tomo.ToMo;
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static String DEFAULT_FILE = "./data/ToMo.txt";
    private ToMo tomo = new ToMo(DEFAULT_FILE);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setToMo(tomo);
            stage.setOnCloseRequest(event -> fxmlLoader.<MainWindow>getController().close());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}