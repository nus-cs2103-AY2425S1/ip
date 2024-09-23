package gui;

import java.io.IOException;

import friday.Friday;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Friday using FXML.
 */
public class Main extends Application {

    private final Friday friday = new Friday("./data/friday.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Friday");
            Image image = new Image(getClass().getResourceAsStream("/images/friday.jpg"));
            stage.getIcons().add(image);
            fxmlLoader.<MainWindow>getController().setFriday(friday);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
