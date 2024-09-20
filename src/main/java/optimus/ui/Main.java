package optimus.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import optimus.Optimus;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Optimus optimus = new Optimus();

    /**
     * Initializes the GUI
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setOptimus(optimus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
