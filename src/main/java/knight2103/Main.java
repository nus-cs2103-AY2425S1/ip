package knight2103;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    private Knight2103 knight2103 = new Knight2103("./savedTaskList.txt"); // hopefully correct destination
    // folder

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // rendering
            Scene scene = new Scene(ap); // arguments varies
            stage.setScene(scene); // Setting the stage to show our scene
            fxmlLoader.<MainWindow>getController().setKnight2103(knight2103);  // inject the Duke instance
            stage.show(); // Render the stage.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

