package spike;

import spike.commands.Command;
import spike.exceptions.SpikeException;
import spike.parser.Parser;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Spike is a simple task manager chatbot that helps users keep track of their tasks.
 * This is the entry point of the Spike application.
 * Initializes the application and starts the interaction with the user.
 */
public class Spike {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Spike object with the specified file path.
     * Initializes the storage, task list and user interface.
     *
     * @param filePath The file path to load and store the tasks.
     */
    public Spike(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (SpikeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the interaction with the user.
     * Reads the user input, processes the command and executes it.
     * Shows the result of the command to the user.
     * Stops the interaction when the user exits the application.
     */
    public void run() {
        ui.showHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SpikeException e) {
                ui.showExceptionMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Spike("data/spike.txt").run();
    }
}
