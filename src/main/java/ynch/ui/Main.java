package ynch.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class Main extends Application {

    private Ynch ynch = new Ynch();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("YNCH");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYnch(ynch);  // inject the Duke instance
            fxmlLoader.<MainWindow>getController().onStartUp();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

