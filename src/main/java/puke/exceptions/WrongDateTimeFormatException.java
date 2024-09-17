package puke.exceptions;

/**
 * Exception thrown when an incorrect date and time format is encountered.
 */
public class WrongDateTimeFormatException extends PukeException {

    /**
     * Constructs a WrongDateTimeFormatException with a message indicating the expected date and time format.
     *
     * @param expectedPattern the expected date and time format pattern.
     */
    public WrongDateTimeFormatException(String expectedPattern) {
        super("OOPS!! Please use the date and time format: \"" + expectedPattern + "\"");
    }
}

