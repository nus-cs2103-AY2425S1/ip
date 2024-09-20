package orion.exceptions;

/**
 * Represents an exception that is thrown when there is an issue saving the task list.
 * <p>
 * This exception indicates that an error occurred during the process of saving the task
 * list, which may involve issues such as file access problems.
 * </p>
 */
public class OrionSaveTaskListException extends OrionException {

    /**
     * Constructs a new {@code OrionSaveTaskListException} with the specified detail message.
     *
     * @param message the detail message that describes the reason for this exception
     */
    public OrionSaveTaskListException(String message) {
        super(message);
    }
}
