package gui;

import java.io.IOException;

import friday.Friday;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Friday using FXML.
 */
public class Main extends Application {

    private Friday friday = new Friday("./data/friday.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFriday(friday);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
