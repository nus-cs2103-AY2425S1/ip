package stelle.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stelle.Stelle;

/**
 * A GUI for Stelle chatbot using JavaFX with FXML.
 * @author Lee Ze Hao (A0276123J)
 */
public class Main extends Application {

    private Stelle stelle = new Stelle();

    /**
     * Starts the application.
     * @param stage The JavaFX stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setStelle(stelle); // inject the Duke instance
            stage.setTitle("Stelle (simulated)");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

