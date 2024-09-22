package donk;

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

    private Donk donk = new Donk("./save.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Donk Chatbot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDonk(donk); // inject the Donk instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
