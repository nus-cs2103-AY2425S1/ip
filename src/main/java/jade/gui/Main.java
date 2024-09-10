package jade.gui;

import java.io.IOException;

import jade.Jade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class that acts as the entry point for the Jade GUI application using FXML.
 */
public class Main extends Application {

    private Jade jade = new Jade();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setJade(jade);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
