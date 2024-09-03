package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import applemazer.Applemazer;
/**
 * A GUI for Applemazer using FXML.
 */
public class Main extends Application {
    Applemazer applemazer = new Applemazer("./data/Applemazer.ser");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            System.out.println(fxmlLoader);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setApplemazer(applemazer);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

