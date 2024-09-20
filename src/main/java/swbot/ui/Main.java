package swbot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import swbot.R2D2;

/**
 * A GUI for R2D2 using FXML.
 */
public class Main extends Application {

    private R2D2 r2d2 = new R2D2();

    /**
     * Initializes and displays the main window of the application.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set
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
            fxmlLoader.<MainWindow>getController().setR2D2(r2d2);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
