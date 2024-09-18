package javafx;

import java.io.File;
import java.io.IOException;

import cook.Cook;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Cook using FXML.
 */
public class Main extends Application {

    private Cook cook = new Cook(new File("data", "tasks"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // inject the Cook instance
            fxmlLoader.<MainWindow>getController().setCook(cook);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
