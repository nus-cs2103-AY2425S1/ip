package vuewee.ui.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import vuewee.ui.TaskListGui;

/**
 * The VueweeGui class is a controller for the GUI of Vuewee. It contains
 * methods to send messages to the user and to handle user input.
 */
public class VueweeGui extends Application {
    public static TaskListGui taskListGui;
    private static MainWindow window;

    public VueweeGui() {
    }

    public static void setTaskListGui(TaskListGui taskListGui) {
        VueweeGui.taskListGui = taskListGui;
    }

    public static void sendMessage(String message) {
        VueweeGui.window.sendBotMessage(message);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(VueweeGui.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            VueweeGui.window = fxmlLoader.<MainWindow>getController();
            stage.show();
            VueweeGui.sendMessage("Hello! I'm Vuewee\nWhat can I do for you?");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
