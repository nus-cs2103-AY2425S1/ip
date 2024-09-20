package tars;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @@author SKarthikeyan28 --reused
 * Code from SE Student Projects JavaFX guide (https://se-education.org/guides/tutorials/javaFx.html)
 * with minor modifications.
 *
 * A GUI for Tars using FXML.
 */
public class Main extends Application {

    private Tars tars = new Tars("./data/Tars.txt");

    public Main() throws IOException {
    }

    /**
     * Starts the JavaFX application by setting up the main window.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTars(tars); //inject Tars instance
            stage.setTitle("Tars");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

