package Azir;

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
    private Azir azir;

    @Override
    public void start(Stage stage) {
        System.out.println("start");
        try {
            System.out.println("yo");
            azir = new Azir();
            System.out.println("reached");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAzir(azir);  // inject the Azir instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

