package controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import processes.MrTracker;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private MrTracker chatbot = new MrTracker();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setChatBot(chatbot); // inject the Duke instance
            fxmlLoader.<MainWindow>getController().welcomeUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
