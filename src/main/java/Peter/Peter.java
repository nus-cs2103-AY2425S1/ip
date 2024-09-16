package peter;

import utilities.CommandParser;
import utilities.Storage;
import utilities.TaskList;

/**
 * Main Class to run controller logic for the application.
 */
public class Peter {
    public static final String FILE_PATH = "./data.txt";
    private Storage store;
    private TaskList tasklist;

    public Peter() {
        this.store = new Storage(FILE_PATH);
        this.tasklist = new TaskList(this.store);
    }

    /**
     * Returns string response based on input command.
     */
    public String getResponse(String input) { 
        return CommandParser.parseCommand(input, tasklist, store);
    }
}
