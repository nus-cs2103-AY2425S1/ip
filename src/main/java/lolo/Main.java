package lolo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lolo.ui.MainWindow;
import java.io.IOException;

public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "./data/lolo.txt";
    private Lolo lolo;

    @Override
    public void init() {
        // Optionally get the file path from system properties or environment variables
        String filePath = System.getProperty("lolo.filepath", DEFAULT_FILE_PATH);
        this.lolo = new Lolo(filePath);
    }


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLolo(lolo);  // inject the Lolo instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
