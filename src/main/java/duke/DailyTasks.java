package duke;

import java.io.IOException;
import java.util.List;

import duke.javafx.MainWindow;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class for the DailyTasks application, a task management tool.
 * Initializes core components such as task list, storage, and UI.
 * Manages the application life cycle and ensures task data is saved on exit.
 *
 * <p>This class extends {@link javafx.application.Application} and initializes
 * components for the JavaFX user interface, linking them with {@link duke.javafx.MainWindow}.</p>
 */
public class DailyTasks extends Application {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Entry point of the JavaFX application.
     * Initializes the primary application components and sets up the main UI window.
     *
     * @param stage Primary stage for this application where the main application scene is set.
     */
    @Override
    public void start(Stage stage) {
        // Assert that the stage is not null
        assert stage != null : "Stage cannot be null";

        stage.setTitle("My JavaFX Application");

        // Add an icon to the window
        Image icon = new Image("/images/DaDuke.png");
        // Assert that the image file exists or is loaded correctly
        assert icon != null : "Icon image cannot be null or missing";
        stage.getIcons().add(icon);

        this.storage = new Storage();
        this.taskList = new TaskList();
        this.ui = new Ui();

        try {
            List<Task> tasks = storage.loadStateFileToTasksList();
            // Assert that tasks list is initialized properly
            assert tasks != null : "Tasks list cannot be null after loading";
            this.taskList.setTasks(tasks);
        } catch (IOException e) {
            System.out.println("Cannot initialize task list!");
        }

        // Assert that the necessary components (taskList, storage, ui) are not null
        assert this.taskList != null : "TaskList cannot be null";
        assert this.storage != null : "Storage cannot be null";
        assert this.ui != null : "Ui cannot be null";

        MainWindow mainWindow = new MainWindow(stage, this.taskList, this.storage, this.ui);
        // Assert that the MainWindow is initialized properly
        assert mainWindow != null : "MainWindow cannot be null after initialization";
    }
}
