package elysia.exception;

/**
 * Represents an invalid date format exception.
 */
public class InvalidDateFormatException extends ElysiaException {
    public InvalidDateFormatException() {
        super("Oh my! I'm so sorry,\n"
                + "but it seems that the date format is invalid.\n"
                +"Let's use the formats like dd-mm-yyyy, yyy-mm-dd, dd mmm, dd mmm yyyy\n"
                + "Here are some examples of valid input:\n"
                + "23 Sep\n"
                + "23rd Sep\n"
                + "23rd Sep 2024\n"
                + "2024-09-23\n"
                + "23/09/2023\n");
    }
}
