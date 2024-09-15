package gui;

import java.io.IOException;

import friendlybot.FriendlyBot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private FriendlyBot friendlyBot = new FriendlyBot("./data/task_list.txt", "./data");

    @Override
    public void start(Stage stage) {
        try {
            MainWindow mainWindow = new MainWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setRoot(mainWindow);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Friendly Bot");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setFriendlyBot(friendlyBot); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
