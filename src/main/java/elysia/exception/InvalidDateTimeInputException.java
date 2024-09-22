package elysia.exception;

/**
 * Represents an invalid date format exception.
 */
public class InvalidDateTimeInputException extends ElysiaException {
    public InvalidDateTimeInputException(String input) {
        super("Oh dear! I think you might have made some mistakes in the date-time input.\n"
                + "The " + input + " is in the past.\n");
    }

    public InvalidDateTimeInputException(String s1, String s2) {
        super("Oh dear! I think you might have made some mistakes in the date-time input.\n"
                + "The " + s1 + " is earlier than " + s2 + ".\n");
    }
}
