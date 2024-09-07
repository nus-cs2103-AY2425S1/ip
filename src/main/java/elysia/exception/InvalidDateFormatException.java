package elysia.exception;

/**
 * Represents an invalid date format exception.
 */
public class InvalidDateFormatException extends ElysiaException {
    public InvalidDateFormatException() {
        super("Oh my! I'm so sorry,\n" +
                "but it seems the date format is invalid.\n" +
                "Let's use the format like: dd-mm-yyyy, yyyy-mm-dd, " +
                "dd mmm, dd mmm yyyy and ?");
    }
}
