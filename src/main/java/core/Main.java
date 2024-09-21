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
    private static final int MIN_HEIGHT = 555;
    private static final int MIN_WIDTH = 777;

    /**
     * Handles the creation of the save file.
     *
     * @param mainController Controller tied to the main GUI view.
     */
    private void handleCreateSaveFile(MainWindow mainController) {
        // Creates the save file
        Pair<Boolean, String> createResult = BROCK.createSaveFile();
        Boolean isSuccessful = createResult.getFirst();
        if (!isSuccessful) {
            mainController.exitProgram();
        }

        // Show response to creating save file
        String createResponse = createResult.getSecond();
        String[] responseParts = createResponse.split(" \\| ");
        String dirResponse = responseParts[0];
        String fileResponse = responseParts[1];
        mainController.showInitialResponse(dirResponse);
        mainController.showInitialResponse(fileResponse);
    }

    /**
     * Handles the loading of tasks from save file.
     *
     * @param mainController Controller tied to the main GUI view.
     */
    private void handleLoadFromSaveFile(MainWindow mainController) {
        // Load the tasks from the save file
        Pair<TaskList, String> loadResult = BROCK.loadTasksFromFile();
        TaskList tasks = loadResult.getFirst();
        if (tasks == null) {
            mainController.exitProgram();
        }
        mainController.setTasks(tasks);

        // Show response to loading tasks
        String loadResponse = loadResult.getSecond();
        mainController.showInitialResponse(loadResponse);
    }

    /**
     * Handles the displaying of welcome message.
     *
     * @param mainController Controller tied to the main GUI view.
     */
    private void handleWelcomeMessage(MainWindow mainController) {
        String welcomeResponse = "Hello! I'm Brock\n"
                + "What can I do for you?";
        mainController.showInitialResponse(welcomeResponse);
    }

    /**
     * Sets the GUI title and icon.
     *
     * @param stage A platform of sorts, as the backbone for the GUI application.
     */
    private void setTitleAndIcon(Stage stage) {
        stage.setTitle("Brock Chatbot");
        Image programIcon = new Image("images/ProgramIcon.jpg");
        stage.getIcons().add(programIcon);
    }

    /**
     * Sets the GUI minimum dimensions.
     *
     * @param stage A platform of sorts, as the backbone for the GUI application.
     */
    private void setMinDimensions(Stage stage) {
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
    }

    /**
     * Starts the GUI.
     *
     * @param stage A platform of sorts, as the backbone for the GUI application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Initialize stuff
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class
                    .getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            MainWindow mainController = fxmlLoader.getController();

            // Crux of setup
            mainController.setBrock(BROCK);
            this.handleCreateSaveFile(mainController);
            this.handleLoadFromSaveFile(mainController);
            this.handleWelcomeMessage(mainController);

            // Finish setup
            Scene scene = new Scene(ap);
            this.setTitleAndIcon(stage);
            this.setMinDimensions(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
