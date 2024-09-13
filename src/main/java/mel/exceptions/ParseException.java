package mel.exceptions;

/**
 * Exception thrown for providing a date and/or
 * time with an invalid format.
 */
public class ParseException extends Exception {
    /**
     * Constructor for ParseException.
     * @param input The date and/or time of invalid format provided.
     */
    public ParseException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "Incorrect date/time: " + super.getMessage();
    }
}
