package rex;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Rex using FXML.
 */
public class Main extends Application {

    private static Stage primaryStage;
    private Rex rex = new Rex();

    @Override
    public void start(Stage stage) {
        try {
            primaryStage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Rex");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRex(rex); // inject the Rex instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exit() {
        primaryStage.close();
    }
}

