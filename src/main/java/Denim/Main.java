package denim;

import denim.Ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private Denim denim = new Denim();

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));

        try {
            VBox mainWindow = fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            stage.setTitle("Denim");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDenim(denim); // inject the Duke instance
            denim.start();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
