package orion.exceptions;

/**
 * Represents an exception that is thrown when there is an issue with task data.
 * <p>
 * This exception indicates that the task data is corrupted or invalid, which
 * might occur if the existing task list is not in the expected format or is otherwise
 * corrupted in the Orion application.
 * </p>
 */
public class OrionTaskDataException extends OrionException {

    /**
     * Constructs a new {@code OrionTaskDataException} with the specified detail message.
     *
     * @param message the detail message that describes the reason for this exception
     */
    public OrionTaskDataException(String message) {
        super(message);
    }
}
