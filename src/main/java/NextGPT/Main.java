package nextgpt;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for NextGPT using FXML.
 */
public class Main extends Application {
    private NextGPT nextGPT = new NextGPT("./data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            // fxml helps to support the view from the control classes to make it cleaner
            // here will be the main parent node with many sub nodes that form a graph of nodes
            AnchorPane ap = fxmlLoader.load();
            // load them onto a scene
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // inject the scene with the nextGPT instance to perform commands on
            fxmlLoader.<MainWindow>getController().setDuke(nextGPT);
            // show on a stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
