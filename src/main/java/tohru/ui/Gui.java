package tohru.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the graphical user interface that the user interacts with the chatbot.
 */
public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Tohru");

            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(220);
            primaryStage.setMinWidth(417);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
