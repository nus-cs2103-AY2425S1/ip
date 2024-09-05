package frontend;

import chatbot.Peridot;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Peridot using FXML.
 */
public class Main extends Application {

    private Peridot peridot = new Peridot("./src/data/chatData.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPeridot(peridot);  // inject the Peridot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}