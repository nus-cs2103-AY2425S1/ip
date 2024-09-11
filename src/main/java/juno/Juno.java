package juno;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import juno.ui.MainWindow;

/**
 * The {@code Juno} class serves as the main entry point for Juno chat bot.
 * It initialises the core components of the chat bot such as the UI, file management, task management,
 * and command parsing. These components instances are then passed around whenever the command or implementation
 * require its functionality.
 * The chat bot also interacts with users, processing their inputs and executing appropriate commands.
 */
public class Juno extends Application {

    /**
     * Starts the Juno chat bot with a GUI. It also loads stored tasks from a file
     * and detect what user has inputted.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Juno.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setField(); // inject the Juno instance
            fxmlLoader.<MainWindow>getController().displayWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
