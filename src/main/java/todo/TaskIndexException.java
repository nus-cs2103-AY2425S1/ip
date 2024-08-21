package todo;

import chatbot.BeeException;

/**
 * A class that formats error message for
 * invalid task index provided.
 *
 * @author celeschai
 */
public class TaskIndexException extends BeeException {

    /**
     * Formats missing task message for different tasks
     */
    public TaskIndexException() {
        super("Hey! This task does not exist.\n" +
                "Type 'list' to see what tasks you have.");
    }
}
