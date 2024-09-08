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
    private Knight2103 knight2103 = new Knight2103("./savedTaskList.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load(); // occur before fxmlLoader.getController() code, if not error

            // Rendering
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKnight2103(knight2103);  // inject the bot instance
            fxmlLoader.<MainWindow>getController().startBotConvo();
            stage.show(); // Render the stage.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

