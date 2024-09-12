package duck;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import duck.components.MainWindow;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String PATH_MAIN_WINDOW = "/view/MainWindow.fxml";
    private final Duck duck = new Duck();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(PATH_MAIN_WINDOW));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDuck(duck); // inject the Duck instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
