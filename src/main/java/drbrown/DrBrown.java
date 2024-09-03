package drbrown;

import drbrown.command.Command;
import drbrown.utils.Parser;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;
import drbrown.utils.DrBrownException;

/**
 * The main class for the DrBrown application, which is a task management program.
 * It handles the initialization, interaction with the user, and execution of commands.
 */
public class DrBrown {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new DrBrown instance with the specified file path for storage.
     * Initializes the UI, loads the task list from storage, and handles any exceptions during loading.
     *
     * @param filePath the file path to load and save tasks
     */
    public DrBrown(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DrBrownException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the DrBrown application.
     * Continuously reads user input, parses commands, executes them, and handles exceptions.
     * The loop terminates when the user issues an exit command.
     */
    public void run() {
        ui.showGreeting();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DrBrownException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method to start the DrBrown application.
     * It creates a new instance of DrBrown with the specified file path and runs the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new DrBrown("data/DrBrown.txt").run();
    }
}
