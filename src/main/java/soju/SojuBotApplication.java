package soju;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class SojuBotApplication extends Application {

    private Soju soju = new Soju();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SojuBotApplication.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Soju Chatbot");
            fxmlLoader.<MainWindow>getController().setSoju(soju); // inject the Soju instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
