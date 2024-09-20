package orion.exceptions;

/**
 * Represents an exception that is thrown for invalid input commands.
 * <p>
 * This exception is used to signal that an input command is not valid or does not
 * meet the expected format or criteria in the Orion application.
 * </p>
 */
public class OrionInputException extends OrionException {

    /**
     * Constructs a new {@code OrionInputException} with the specified detail message.
     *
     * @param message the detail message that describes the reason for this exception
     */
    public OrionInputException(String message) {
        super(message);
    }
}
