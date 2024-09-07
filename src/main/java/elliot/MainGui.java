package elliot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;

/**
 * The main GUI class engaging all UI sub classes as well as Elliot class.
 */
public class MainGui extends Application {
    private Elliot elliot = new Elliot();

    /**
     * Begins the UI stage javafx.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainGui.class
                    .getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setElliot(elliot); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
