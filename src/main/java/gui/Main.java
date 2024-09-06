package gui;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import main.Bean;

/**
 * A GUI for Bean using FXML.
 */
public class Main extends Application {

    private Bean bean = new Bean();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBean(bean);  // inject the Bean instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}