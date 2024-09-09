package chatbot;

import java.io.IOException;

import chatbot.logic.Bobby;
import chatbot.views.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the chatbot application
 */
public class Main extends Application {
    /** Represents the chatbot logic */
    private Bobby bobby = new Bobby();

    /**
     * Sets the appropriate scene and shows the stage
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        assert this.bobby != null : "bobby should not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBobby(bobby); // inject in the bobby instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
