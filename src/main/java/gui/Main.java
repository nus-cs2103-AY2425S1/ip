package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import regina.Regina;

/**
 * A GUI application for the Regina chatbot using FXML.
 * This class initializes the JavaFX application and sets up the main user interface.
 */
public class Main extends Application {

    private final Regina regina = new Regina();

    /**
     * The main entry point for the JavaFX application.
     * This method is called when the application is launched.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRegina(regina);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
