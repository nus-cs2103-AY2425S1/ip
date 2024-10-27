package rotodo.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rotodo.RoTodo;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static String userName = "User";
    private RoTodo roTodo = new RoTodo();

    public static void setUserName(String name) {
        userName = name;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRoTodo(roTodo); // inject the RoTodo instance
            fxmlLoader.<MainWindow>getController().onStart(userName);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
