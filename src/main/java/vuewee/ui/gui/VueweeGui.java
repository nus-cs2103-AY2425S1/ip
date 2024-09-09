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
    private static TaskListGui taskListGui;
    private static MainWindow window;

    public VueweeGui() {
    }

    public static void setTaskListGui(TaskListGui taskListGui) {
        VueweeGui.taskListGui = taskListGui;
    }

    public static void sendMessage(String message) {
        VueweeGui.window.sendBotMessage(message);
    }

    public static void processUserInput(String userInput) {
        VueweeGui.taskListGui.processInput(userInput);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(VueweeGui.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            VueweeGui.window = fxmlLoader.<MainWindow>getController();
            stage.show();
            VueweeGui.sendMessage("Hello! I'm Vuewee\nWhat can I do for you?");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
