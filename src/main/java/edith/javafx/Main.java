package edith.javafx;

import java.io.IOException;

import edith.Edith;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for edith.javafx.Duke using FXML.
 */
public class Main extends Application {

    private Edith edith = new Edith();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Edith: Your Personal Chatbot");
            fxmlLoader.<MainWindow>getController().setEdith(edith);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
