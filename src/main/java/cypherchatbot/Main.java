package cypherchatbot;

import java.io.IOException;

import cypherchatbot.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final static String FILEPATH = "./data/tasks.txt";
    private Cypher cypher = new Cypher(FILEPATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCypher(this.cypher);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

