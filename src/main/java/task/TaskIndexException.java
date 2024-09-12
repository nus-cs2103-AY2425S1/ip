package task;

import chatbot.BeeException;

/**
 * A class that formats error message for
 * invalid task index provided.
 *
 * @author celeschai
 */
public class TaskIndexException extends BeeException {

    /**
     * Formats missing task message for different tasks.
     */
    public TaskIndexException() {
        super("""
                This task does not exist.
                Remember to use the task's index number.
                Type 'list' to see what tasks you have.""");
    }
}
