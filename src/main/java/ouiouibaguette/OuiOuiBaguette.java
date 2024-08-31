package ouiouibaguette;

import command.Command;
import parser.Parser;
import storage.FileStorage;
import storage.Storage;
import tasklist.TaskList;

/**
 * The main class for the OuiOuiBaguette application.
 * It initializes the necessary components and handles the main application
 * loop.
 */
public class OuiOuiBaguette {

    /** The storage system used to persist tasks. */
    private Storage storage;

    /** The task list that manages the user's tasks. */
    private TaskList tasks;

    /** The parser that interprets user input and converts it into commands. */
    private Parser parser;

    /** The flag that keeps track if the application should exit */
    private boolean shouldExit = false;

    /**
     * Constructs the OuiOuiBaguette application with the specified directory path
     * for storage.
     * Initializes the user interface, storage, task list, and parser.
     *
     * @param dirPath The directory path where task data will be stored.
     */
    public OuiOuiBaguette(String dirPath) {
        // ui = new CommandLineUi();
        storage = new FileStorage(dirPath);
        tasks = new TaskList(storage);
        parser = new Parser();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parseCommand(input);
            if (c.isExit()) {
                shouldExit = true;
            }

            return c.execute(tasks);

        } catch (OuiOuiBaguetteException e) {
            return (e.getMessage());
        }
    }

    /**
     * Greets the user with a welcome message.
     */
    public String greet() {
        return "Bone-jaw! I'm Oui Oui Baguette.\nWhat can I do for you? Oui Oui";
    }

    /**
     * Bids farewell to the user with a goodbye message.
     */
    public String farewell() {
        return "Bye. Hope to see you soon! Oui Oui";
    }

    /**
     * Getter function to check if chatbot should exit.
     */
    public boolean isExit() {
        return shouldExit;
    }

}
