package king.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import king.King;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private King king = new King();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinWidth(400); // Set minimum width
            stage.setMinHeight(600); // Set minimum height
            fxmlLoader.<MainWindow>getController().setKing(king); // inject the King instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
