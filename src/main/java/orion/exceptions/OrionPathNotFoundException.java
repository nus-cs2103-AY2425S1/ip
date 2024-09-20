package orion.exceptions;

/**
 * Represents an exception that is thrown when a specified path cannot be found.
 * <p>
 * This exception is used to indicate that a requested path, such as a file or directory,
 * does not exist or cannot be located in the Orion application. It is thrown when Orion
 * Storage is unable to create a new file to store the user task list.
 * </p>
 */
public class OrionPathNotFoundException extends OrionException {

    /**
     * Constructs a new {@code OrionPathNotFoundException} with the specified detail message.
     *
     * @param message the detail message that describes the reason for this exception
     */
    public OrionPathNotFoundException(String message) {
        super(message);
    }
}
