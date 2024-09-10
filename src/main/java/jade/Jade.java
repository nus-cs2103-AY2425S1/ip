package jade;

import jade.command.Command;
import jade.exception.JadeException;
import jade.parser.Parser;
import jade.storage.Storage;
import jade.task.TaskManager;
import jade.ui.Ui;

/**
 * The main entry point for the Jade application.
 * Initialises the storage, task manager, and user interface, and starts the application.
 */
public class Jade {
    private static final String FILE_PATH = "./data/jade.txt";
    private static final Storage storage = new Storage(FILE_PATH);
    private static final TaskManager taskManager = new TaskManager(storage);
    private static final Parser parser = new Parser();

    /**
     * Main method to start the Jade application.
     * Initialises the Storage, TaskManager, and UI, and then runs the UI.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Ui ui = new Ui(taskManager, parser);
        ui.run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input, taskManager, parser);
            return command.runForGui();
        } catch (JadeException e) {
            return e.getMessage();
        }
    }
}
