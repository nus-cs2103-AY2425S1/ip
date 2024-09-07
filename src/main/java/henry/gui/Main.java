package henry.gui;

import java.io.IOException;

import henry.Henry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Henry using FXML.
 */
public class Main extends Application {

    private Henry henry = new Henry("./data/Henry.txt");

    /**
     * Starts the GUI application for Henry.
     *
     * @param stage The primary stage for this application, onto which the application
     *              scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHenry(henry); // inject the Henry instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

