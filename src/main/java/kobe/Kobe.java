package kobe;

import kobe.command.Command;
import kobe.task.TaskList;
import kobe.util.Parser;
import kobe.util.Storage;
import kobe.util.Ui;

import java.io.IOException;

/**
 * The main class of the Kobe chatbot application.
 * Handles the initialization of the application and runs the main program loop.
 */
public class Kobe {

    /** Handles storage-related operations such as saving and loading tasks. */
    private Storage storage;

    /** Manages the list of tasks in the application. */
    private TaskList tasks;

    /** Manages user interactions, such as displaying messages and reading input. */
    private Ui ui;

    /**
     * Constructs the Kobe chatbot application with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Kobe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Kobe chatbot application, processing user commands until an exit command is given.
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
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the Kobe chatbot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Kobe("data/kobee.txt").run();
    }
}
