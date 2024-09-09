import java.io.IOException;

import alice.Alice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** A GUI for Alice using FXML. */
public class Main extends Application {

    private final Alice alice = new Alice();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setAlice(alice);
            fxmlLoader.<MainWindow>getController().getGreeting();
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
