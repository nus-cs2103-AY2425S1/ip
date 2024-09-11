package blitz;

import command.Command;
import exception.BlitzException;

/**
 * Represents the main class for the Blitz application, responsible for initializing and running the application.
 */
public class Blitz {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new Blitz object with specified file path, divider and tab.
     *
     * @param path File path to read and store the tasks.
     */
    public Blitz(String path) throws BlitzException {
        this.ui = new Ui();
        this.storage = new Storage(path);
        this.taskList = storage.readFromFile();

        assert this.taskList != null : "TaskList must be properly initialized after reading from file";
    }


    /**
     * Parses the given input (command) String and returns a response based on the parsed command.
     *
     * @param inp Input (command) String to be processed.
     * @return The response from executing the command if no exception, return exception message otherwise.
     */
    public String getResponse(String inp) {
        try {
            Command command = Parser.parse(inp);

            this.taskList = storage.readFromFile();

            assert this.taskList != null : "TaskList must be properly initialized after reading from file";

            return command.execute(this.taskList, this.ui, this.storage);
        } catch (BlitzException e) {
            return e.toString();
        }
    }
}
