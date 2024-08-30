package lexi;

import lexi.command.Command;
import lexi.exception.LexiException;
import lexi.parser.Parser;
import lexi.storage.Storage;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * The main class for the Lexi application.
 * This class initializes the application, loads the necessary resources, and runs the main loop.
 */
public class Lexi {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Lexi application instance.
     * Initializes the UI, loads tasks from the specified file, and prepares the task list.
     *
     * @param filePath The file path to load tasks from.
     */
    public Lexi(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (LexiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Lexi application.
     * This method starts the main loop that reads commands from the user, processes them,
     * and executes the corresponding actions until the user decides to exit.
     */
    public void run() {
        ui.showLine();
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.showLine();
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LexiException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point for the Lexi application.
     * This method creates a new Lexi instance and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Lexi("data/tasks.txt").run();
    }
}
