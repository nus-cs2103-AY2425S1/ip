package duke;

import java.io.IOException;
import java.util.List;

import duke.javafx.MainWindow;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class for the DailyTasks application, which acts as a task manager.
 * It handles initializing the application, managing the task list, and saving
 * the application state before exiting.
 *
 * <p>This class extends {@link javafx.application.Application} and serves as
 * the entry point for the JavaFX application. It initializes core components
 * such as the task list, storage, and user interface, and passes these
 * components to the {@link duke.javafx.MainWindow} for UI management.</p>
 */
public class DailyTasks extends Application {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The entry point of the JavaFX application. This method is called after the
     * application is launched and is responsible for initializing core components
     * and setting up the main window.
     *
     * @param stage The primary stage for this application, onto which the main application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.ui = new Ui();

        try {
            List<Task> tasks = storage.loadStateFileToTasksList();
            this.taskList.setTasks(tasks);
        } catch (IOException e) {
            System.out.println("Cannot initialize task list!");
        }

        MainWindow mainWindow = new MainWindow(stage, this.taskList, this.storage, this.ui);
    }

    /**
     * The main method that serves as the entry point for the application.
     * It calls the {@link #launch(String...)} method to start the JavaFX application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
