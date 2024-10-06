package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the user inputs a list number
 * that cannot be used to index the list. This exception provides a custom
 * error message to notify the user about the invalid list number.
 */
public class InvalidIndexException extends WenJieException {

    /**
     * Constructs an InvalidIndexException with a default error message.
     */
    public InvalidIndexException() {
        super("test");
    }

    /**
     * Returns a custom error message that informs the user they have input
     * a list number that cannot be used to index the list.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "wah tough, you input an invalid number that can't be used to index the list";
    }
}
