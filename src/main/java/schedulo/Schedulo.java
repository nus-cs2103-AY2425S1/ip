package schedulo;

import command.Command;
import exception.ScheduloException;
import task.TaskList;
import util.Parser;
import util.Storage;
import javafx.application.Application;
import java.io.IOException;

/**
 * The main class for the Schedulo application.
 * This class is responsible for initializing the application, loading tasks from storage,
 * and handling the main application loop where user commands are processed.
 */
public class Schedulo {
    private Storage storage;
    private TaskList tasks;
    private Command c;

    /**
     * Constructs a Schedulo instance and initializes its components.
     * Loads the task list from the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Schedulo(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main application loop.
     * This method reads user commands, processes them, and interacts with the user interface.
     */
    public String run(String fullCommand) {
        // boolean isExit = false;
        try {
            this.c = Parser.parse(fullCommand);
            return  this.c.execute(tasks, storage);
            // isExit = c.isExit();
        } catch (ScheduloException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * The main method that serves as the entry point to the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // new Schedulo("data/data.txt").run();
        Application.launch(App.class, args);
    }

    public String getResponse(String input) {
        return this.run(input);
    }

    public Command getCommand() {
        return this.c;
    }
}
