package lutodo.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lutodo.LuToDo;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private LuToDo luToDo = new LuToDo("taskListFile.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("LuToDo");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(luToDo);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


