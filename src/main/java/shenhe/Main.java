package shenhe;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Shenhe using FXML.
 */
public class Main extends Application {
    private static final String filepath = "data/shenhe.txt";
    private Shenhe shenhe = new Shenhe(filepath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setShenhe(shenhe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
