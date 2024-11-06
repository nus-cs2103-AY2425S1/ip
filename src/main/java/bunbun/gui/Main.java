package bunbun.gui;

import bunbun.Bunbun;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Bunbun using FXML.
 */
public class Main extends Application {

    private Bunbun bunbun = new Bunbun("data");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bunbun");
            fxmlLoader.<MainWindow>getController().setBunbun(bunbun);  // inject the Bunbun instance
            stage.setOnCloseRequest(e -> bunbun.close());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

