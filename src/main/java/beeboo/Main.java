package beeboo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the BeeBoo application. It extends the JavaFX
 * {@link javafx.application.Application} class and is responsible for initializing and launching
 * the GUI.
 */
public class Main extends Application {

    private Beeboo beeboo = new Beeboo("./data/beeboo.txt");

    /**
     * The start method is called after the JavaFX application is initialized. It sets up the primary
     * stage, loads the FXML layout, and displays the GUI.
     *
     * @param stage the primary stage for this JavaFX application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBeeboo(beeboo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
