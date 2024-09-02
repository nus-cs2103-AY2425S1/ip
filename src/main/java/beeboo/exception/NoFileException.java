package beeboo.exception;

/**
 * Represents an exception that is thrown when no data file is found.
 * This exception is used to indicate that the task list data file is missing and needs to be created.
 */
public class NoFileException extends BeeBooExceptions {

    /**
     * Constructs a NoFileException with a specific error message.
     *
     * @param error The error message associated with this exception.
     */
    public NoFileException(String error) {
        super(error);
    }

    /**
     * Returns a string representation of this exception, indicating that no data file is present.
     *
     * @return A message stating that there is no data and a new TaskList will be created.
     */
    @Override
    public String toString() {
        return "You don't have any data right now. Let me create a new TaskList";
    }
}
