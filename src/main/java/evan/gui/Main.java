package evan.gui;

import java.io.IOException;

import evan.service.Evan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Evan using FXML.
 */
public class Main extends Application {
    private final Evan evan = new Evan("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(evan.gui.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEvan(evan); // Injects the Evan instance
            stage.show();
        } catch (IOException e) {
            // e.printStackTrace(); TODO: Remove this comment
            System.out.println("An error occurred when loading the GUI.");
        }
    }
}
