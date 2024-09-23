package llama;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class to start the Llama Program up
 */
public class Main extends Application {
    private static Stage stage;
    private Llama llama = new Llama();

    @Override
    public void start(Stage stage) {
        try {
            Main.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Llama");
            fxmlLoader.<MainWindow>getController().setLlama(llama);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
