package revir.system.Exceptions;

/**
 * Represents an exception that is thrown when an invalid format is encountered.
 * This exception is typically thrown when a format different from the expected format is encountered.
 */
public class InvalidFormatException extends Exception {
    /**
     * Constructs a new InvalidFormatException with the specified format.
     *
     * @param format the expected format that caused the exception
     */
    public InvalidFormatException(String format) {
        super("Invalid Format. Expected: " + format);
    }
}
