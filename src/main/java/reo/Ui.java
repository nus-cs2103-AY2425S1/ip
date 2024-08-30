package reo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A GUI for Duke using FXML.
 */
public class Ui extends Application {

    private Reo reo = new Reo();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Storage storage = new Storage("./data/reo.txt");
            TaskList tasklist;
            try {
                tasklist = new TaskList(storage.readFile());
            } catch (Exception e) {
                tasklist = new TaskList(new ArrayList<Task>());
            }
            storage = new Storage("./data/reo.txt");
            fxmlLoader.<MainWindow>getController().setProperties(tasklist, storage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
