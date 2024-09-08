package jeff;

import jeff.command.Command;
import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.TaskList;

/**
 * Represents a chatbot.
 */
public class Jeff {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for the Jeff Class.
     * Uses the given file path input to get the task list from the text file.
     * Also initialises the user interface.
     *
     * @param filePath File path of the task list text file.
     */
    public Jeff(String filePath) {
        this.storage = new Storage(filePath);
        assert this.storage != null : "Storage should not be null";

        // Load the task list from the task list text file
        try {
            this.tasks = new TaskList(this.storage.loadTaskListFromDatabase());
            assert this.tasks != null : "Task list should not be null";
        } catch (JeffException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns the string representation of the response of the chatbot Jeff based on the user's input.
     *
     * @param input Given input.
     * @return Jeff's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Command should not be null";

            return c.execute(this.tasks, this.storage);
        } catch (JeffException e) {
            return Parser.addSpaceInFrontOfEachLine(e.toString());
        }
    }
}
