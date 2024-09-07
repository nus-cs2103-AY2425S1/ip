package arsenbot.task;

/**
 * Represents an exception that is specific to the task manager.
 * This exception is thrown when there is an error related to task management,
 * such as invalid user input or issues with tasks.
 */
public class TaskManagerException extends Exception {
    public TaskManagerException(String message) {
        super(message);
    }
}