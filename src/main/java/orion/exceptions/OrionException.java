package orion.exceptions;

/**
 * Represents a base exception class for all exceptions related to the Orion application.
 * <p>
 * This abstract class extends {@link Exception} and is used as a base class for
 * custom exceptions in the Orion application. It allows for the creation of specific
 * exception types with custom error messages.
 * </p>
 */
public abstract class OrionException extends Exception {

    /**
     * Constructs a new {@code OrionException} with the specified detail message.
     *
     * @param message the detail message that describes the reason for this exception
     */
    public OrionException(String message) {
        super(message);
    }
}