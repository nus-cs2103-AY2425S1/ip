package joe.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import joe.main.Joe;

/**
 * A GUI for Joe using FXML.
 */
public class Main extends Application {

    private final Joe joe = new Joe("joe.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);

            Image icon = new Image(this.getClass().getResourceAsStream("/images/Joe.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Joe the Chatbot");

            fxmlLoader.<MainWindow>getController().setJoe(joe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


