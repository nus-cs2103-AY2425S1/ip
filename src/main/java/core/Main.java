package core;

import java.io.IOException;

import controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import task.TaskList;
import utility.Pair;

/**
 * A GUI for core.Brock using FXML.
 */
public class Main extends Application {
    private final Brock BROCK = new Brock();

    @Override
    public void start(Stage stage) {
        try {
            // Setup
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class
                    .getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            MainWindow mainController = fxmlLoader.getController();

            // Inject the brock instance
            mainController.setBrock(BROCK);

            // Creates the save file
            Pair<Boolean, String> createResult = BROCK.createSaveFile();
            Boolean isSuccessful = createResult.getFirst();
            if (!isSuccessful) {
                mainController.exitProgram();
            }
            String createResponse = createResult.getSecond();
            mainController.showInitialMessage(createResponse);

            // Load the tasks from the save file
            Pair<TaskList, String> loadResult = BROCK.loadTasksFromFile();
            TaskList tasks = loadResult.getFirst();
            if (tasks == null) {
                mainController.exitProgram();
            }
            String loadResponse = loadResult.getSecond();
            mainController.showInitialMessage(loadResponse);
            mainController.setTasks(tasks);

            // Shows welcome message
            String welcomeResponse = "Hello! I'm core.Brock\n"
                    + "What can I do for you?";
            mainController.showInitialMessage(welcomeResponse);

            // Finish setup
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
