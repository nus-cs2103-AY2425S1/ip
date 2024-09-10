package core;

import java.io.IOException;

import controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import task.TaskList;
import utility.Pair;

/**
 * A GUI for Brock using FXML.
 */
public class Main extends Application {
    private static final Brock BROCK = new Brock();

    /**
     * Starts the GUI.
     *
     * @param stage A platform of sorts, as the backbone for the GUI application.
     */
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
            String[] responseParts = createResponse.split(" \\| ");
            String dirResponse = responseParts[0];
            String fileResponse = responseParts[1];
            mainController.showInitialResponse(dirResponse);
            mainController.showInitialResponse(fileResponse);

            // Load the tasks from the save file
            Pair<TaskList, String> loadResult = BROCK.loadTasksFromFile();
            TaskList tasks = loadResult.getFirst();
            if (tasks == null) {
                mainController.exitProgram();
            }
            String loadResponse = loadResult.getSecond();
            mainController.showInitialResponse(loadResponse);
            mainController.setTasks(tasks);

            // Shows welcome message
            String welcomeResponse = "Hello! I'm Brock\n"
                    + "What can I do for you?";
            mainController.showInitialResponse(welcomeResponse);

            // Finish setup
            Scene scene = new Scene(ap);

            // Set program title and icon
            stage.setTitle("Brock Chatbot");
            Image programIcon = new Image("images/ProgramIcon.jpg");
            stage.getIcons().add(programIcon);

            // Set & show stage
            stage.setScene(scene);
            stage.setMinHeight(555);
            stage.setMinWidth(777);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
