package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;

/**
 * The main class to start the ChattyBuddy GUI application.
 */
public class Main extends Application {

    private ChattyBuddy chattyBuddy = new ChattyBuddy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane apn = fxmlLoader.load();
            Scene scene = new Scene(apn);
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setChattyBuddy(chattyBuddy);
            mainWindow.showWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * main.Main method to launch the application.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
