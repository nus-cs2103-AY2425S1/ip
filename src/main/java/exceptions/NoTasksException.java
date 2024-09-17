package exceptions;

/**
 * Represents a class for an Exception where task list is empty.
 */
public class NoTasksException extends Exception {

    /**
     * Represents a constructor for a NoTasksException.
     */
    public NoTasksException() {
        super("There are no tasks in the list");
    }
}
