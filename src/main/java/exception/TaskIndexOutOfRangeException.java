package exception;

/**
 * Exception for task index out of range.
 *
 * @author dwsc37.
 */
public class TaskIndexOutOfRangeException extends BotException {
    /**
     * Constructor for a <code>TaskIndexOutOfRangeException</code>.
     * @param taskIndex Task index inputted by the user.
     * @param taskSummary Summary of the current task list.
     */
    public TaskIndexOutOfRangeException(int taskIndex, String taskSummary) {
        super("Index " + taskIndex + " is out of range!\n" + taskSummary);
    }
}
