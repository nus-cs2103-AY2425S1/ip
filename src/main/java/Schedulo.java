import java.io.IOException;

import command.Command;
import exception.ScheduloException;
import task.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

/**
 * The main class for the Schedulo application.
 * This class is responsible for initializing the application, loading tasks from storage,
 * and handling the main application loop where user commands are processed.
 */
public class Schedulo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Schedulo instance and initializes its components.
     * Loads the task list from the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Schedulo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main application loop.
     * This method reads user commands, processes them, and interacts with the user interface.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ScheduloException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * The main method that serves as the entry point to the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Schedulo("data/data.txt").run();
    }
}