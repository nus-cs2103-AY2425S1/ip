package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import screwllum.Screwllum;

/**
 * A GUI for Screwllum using FXML.
 */
public class Main extends Application {

    private Screwllum screwllum = new Screwllum();

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Screwllum");
            fxmlLoader.<MainWindow>getController().setScrewllum(screwllum); // Inject the Screwllum instance
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();
            stage.setOnCloseRequest(e -> {
                e.consume();
                fxmlLoader.<MainWindow>getController().exit();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
