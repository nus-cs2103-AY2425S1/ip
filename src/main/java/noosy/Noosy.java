package noosy;

import noosy.commands.Command;
import noosy.exception.NoosyException;
import noosy.parser.Parser;
import noosy.storage.Storage;
import noosy.task.TaskList;
import noosy.ui.Ui;

import java.io.IOException;

/**
 * The main class for the Noosy task management system.
 * This class initializes the system components and handles the main execution loop.
 */
public class Noosy {

    /** The storage component for persisting tasks. */
    private Storage storage;

    /** The list of tasks managed by the system. */
    private TaskList tasks;

    /** The user interface component. */
    private Ui ui;

    /**
     * Constructs a new Noosy task management chatbot.
     * Initializes the UI, storage, and task list components.
     *
     * @param filePath The file path for storing tasks.
     */
    public Noosy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NoosyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the main execution loop of the Noosy chatbot.
     * Continuously reads user commands, executes them, and displays results until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NoosyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point for the Noosy application.
     * Creates a new Noosy instance and starts its execution.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Noosy("data/noosy.txt").run();
    }
}