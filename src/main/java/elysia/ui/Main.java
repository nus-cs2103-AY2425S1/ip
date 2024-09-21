package elysia.ui;

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

    private Elysia elysia = new Elysia();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(elysia);  // inject the Duke instance
            fxmlLoader.<MainWindow>getController().showWelcome();
            fxmlLoader.<MainWindow>getController().showFileMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
