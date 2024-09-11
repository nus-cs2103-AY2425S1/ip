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
        assert stage != null : "Stage should not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane should not be null after loading FXML";

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            assert fxmlLoader.getController() instanceof MainWindow : "Controller should be an instance of MainWindow";
            fxmlLoader.<MainWindow>getController().setJade(jade);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
