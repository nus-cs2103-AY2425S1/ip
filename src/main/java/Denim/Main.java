package denim;

import java.io.IOException;

import denim.exceptions.DenimDirectoryException;
import denim.exceptions.DenimFileException;
import denim.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The Main class is the entry point for the Denim application, extending JavaFX's Application class.
 * It initializes the main window from the FXML file, sets up the primary scene, and displays the stage.
 * An instance of Denim is created and injected into the MainWindow controller to manage application interactions.
 * Any IOExceptions during the FXML loading are handled and wrapped in a RuntimeException.
 */
public class Main extends Application {

    public static final String FILE_PATH = "data/denim.txt";

    private final Denim denim = new Denim(FILE_PATH);

    @Override
    public void start(Stage stage) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));

        try {
            VBox mainWindow = fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            stage.setTitle("Denim");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().injectDenim(denim); // inject the Denim instance
            denim.start();
            stage.show();
            fxmlLoader.<MainWindow>getController().displayGreetingMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DenimFileException e) {
            fxmlLoader.<MainWindow>getController().handleFileNotFound();
            stage.show();
            fxmlLoader.<MainWindow>getController().displayGreetingMessage();
        } catch (DenimDirectoryException e) {
            fxmlLoader.<MainWindow>getController().handleDirectoryNotFound();
            stage.show();
            fxmlLoader.<MainWindow>getController().displayGreetingMessage();
        }

    }
}

