package lexi.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lexi.Lexi;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Lexi lexi = new Lexi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Lexi Chatbot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLexi(lexi); // inject the Lexi instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
