package knight2103;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
    @FXML
    private VBox dialogContainer;
    private Knight2103 knight2103 = new Knight2103("./savedTaskList.txt"); // creates instance, will load
    // file storage already.

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // rendering
            Scene scene = new Scene(ap); // arguments varies
            stage.setScene(scene); // Setting the stage to show our scene
            fxmlLoader.<MainWindow>getController().setKnight2103(knight2103);  // inject the bot instance,
            // code must come AFTER ap = fxmlLoader.load(), if not error
            fxmlLoader.<MainWindow>getController().startBotConvo();
            stage.show(); // Render the stage.

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

