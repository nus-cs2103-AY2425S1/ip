package krona;

import krona.command.*;
import krona.task.*;
import krona.storage.Storage;
import krona.ui.Ui;
import krona.parser.Parser;
import krona.exception.KronaException;

/**
 * Main class for Krona chatbot
 */
public class Krona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the new Krona.
     * Initializes the user interface, storage, and task list.
     * Attempts to load existing tasks from the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Krona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KronaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main Krona application.
     * Continuously reads and executes user commands until the bye command is given.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            try {
                c.execute(tasks, ui, storage);
            } catch (KronaException e) {
                ui.showMessage("An error occurred: " + e.getMessage());
            }
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Krona("data/tasks.txt").run();
    }

}