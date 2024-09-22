package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the user inputs a list number
 * that exceeds the current bounds of the list. This exception provides a custom
 * error message to notify the user about the invalid list number.
 */
public class OutOfBoundsException extends WenJieException {

    /**
     * Constructs an OutOfBoundsException with a default error message.
     */
    public OutOfBoundsException() {
        super("test");
    }

    /**
     * Returns a custom error message that informs the user they have input
     * a list number that has not yet been added to the list.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "wah shag bro, you input a list number that hasn't been added to the list yet";
    }
}
