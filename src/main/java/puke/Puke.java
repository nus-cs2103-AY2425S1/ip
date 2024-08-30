package puke;

import puke.exceptions.PukeException;
import puke.handlers.InputManager;
import puke.storage.Storage;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;
import puke.ui.Ui;

/**
 * The main class for the Puke application. Initializes and starts the user interface.
 */
public class Puke {
    private MessageBuilder messageBuilder;
    private TaskManager taskManager;
    private InputManager inputManager;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a Puke instance, initializing all necessary components for the application.
     */
    public Puke() {
        this.messageBuilder = new MessageBuilder();
        this.storage = new Storage();
        this.taskManager = new TaskManager(storage.loadTasks());
        this.inputManager = new InputManager(taskManager, messageBuilder);
        this.ui = new Ui(inputManager, messageBuilder);
    }

    /**
     * The main entry point for the application. Creates a Puke instance and starts the user interface.
     *
     * @param args command-line arguments
     * @throws PukeException if an error occurs during the execution of the user interface
     */
    public static void main(String[] args) throws PukeException {
        Puke chatBot = new Puke();
        chatBot.ui.start();
    }
}
