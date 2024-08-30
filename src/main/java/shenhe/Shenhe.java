package shenhe;

import shenhe.command.Command;
import shenhe.parser.Parser;

/**
 * Represents the main entry point for the Shenhe application.
 * <p>
 * The {@code Shenhe} class is responsible for initializing and running the application.
 * It handles loading tasks from storage, reading user commands, executing commands,
 * and managing the application state.
 * </p>
 */
public class Shenhe {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Shenhe} object with the specified file path for storing tasks.
     * <p>
     * Initializes the user interface, storage, and loads the tasks from the specified file.
     * If loading tasks fails, an empty task list is initialized.
     * </p>
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Shenhe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application, which continuously reads and processes user commands
     * until an exit command is issued.
     * <p>
     * In each iteration, a command is read from the user, parsed, and executed.
     * The loop continues until the command indicates that the application should exit.
     * </p>
     */
    public void run() {
        ui.showLine();
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the application. Initializes a {@code Shenhe} instance
     * with the specified file path and starts the application by calling {@link #run()}.
     *
     * @param args Command-line arguments. Not used in this implementation.
     */
    public static void main(String[] args) {
        new Shenhe("data/Shenhe.txt").run();
    }
}
