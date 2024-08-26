package killua;

import killua.command.Command;
import killua.util.*;

import java.io.IOException;

/**
 * Represents the main application class for the Killua task manager.
 * It handles the initialization of the user interface (UI), storage, and task list,
 * and manages the main application loop where user commands are processed.
 */
public class Killua {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Killua instance with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Killua(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Killua application. It displays a welcome message, reads user commands,
     * parses and executes them, and continues until an exit command is received.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandStr = ui.readCommand();
                Command command = Parser.parseCommand(commandStr);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (KilluaException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the Killua application.
     * It initializes the Killua instance with the default file path and starts the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Killua("./data/tasks.txt").run();
    }
}
