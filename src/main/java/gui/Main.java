package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import testament.Testament;

/**
 * A GUI for Testament using FXML.
 */
public class Main extends Application {

    private Testament testament = new Testament();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Testament");
            fxmlLoader.<MainWindow>getController().setTestament(testament); // inject the Testament instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
