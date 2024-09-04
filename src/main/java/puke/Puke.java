package puke;

import puke.exceptions.PukeException;
import puke.message.MessageBuilder;

/**
 * The main class for the Puke application. Initializes and starts the user interface.
 */
public class Puke {
    private MessageBuilder messageBuilder;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a Puke instance, initializing all necessary components for the application.
     */
    public Puke() {
        this.messageBuilder = new MessageBuilder();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadTasks());
        this.parser = new Parser(taskList, messageBuilder);
        this.ui = new Ui(parser, messageBuilder);
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
