package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the user a number with decimal points
 * that is not a pure integer. This exception provides a custom
 * error message to notify the user about the invalid number input.
 */
public class InvalidNumberException extends WenJieException {

    /**
     * Constructs an InvalidNumberException with a default error message.
     */
    public InvalidNumberException() {
        super("test");
    }

    /**
     * Returns a custom error message that informs the user they have input
     * a list number that cannot be parsed properly.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "oi you don't funny funny use weird numbers give me decimal points hor, I want pure integers PLSS";
    }
}
