package dude.exception;

/**
 * Thrown to indicate that a required numerical input is not a valid number.
 */
public class DudeNumberException extends DudeException {

    /**
     * Constructs a new DudeNumberException with a detailed message
     * indicating that the provided input is not a valid number.
     *
     * @param input The input string that could not be parsed as a number.
     */
    public DudeNumberException(String input) {
        super("This is very wrong. "
                + "\"" + input + "\" is not a valid number.");
    }
}
