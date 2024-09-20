package wolfie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Wolfie wolfie = new Wolfie("data/tasks.txt");

    /**
     * Starts the application and sets the width and height of the window.
     *
     * @param stage The stage to start the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWolfie(wolfie); // inject the Wolfie instance
            stage.setTitle("Wolfie");
            stage.setHeight(600);
            stage.setWidth(700);
            stage.setResizable(true); // Allow window resizing
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
