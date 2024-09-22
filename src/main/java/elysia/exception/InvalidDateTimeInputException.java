package elysia.exception;

/**
 * Represents an invalid date format exception.
 */
public class InvalidDateTimeInputException extends ElysiaException {
    /**
     * Constructs an {@code InvalidDateTimeInputException} with a message indicating that the provided date-time input
     * is in the past.
     *
     * @param input the date-time input that is invalid because it is earlier than the current time.
     */
    public InvalidDateTimeInputException(String input) {
        super("Oh dear! I think you might have made some mistakes in the date-time input.\n"
                + "The " + input + " is in the past.\n");
    }

    /**
     * Constructs an {@code InvalidDateTimeInputException} with a message indicating that the first date-time input is
     * earlier than the second.
     *
     * @param s1 the first date-time input that is invalid because it is earlier
     * @param s2 the second date-time input that the first is being compared to
     */
    public InvalidDateTimeInputException(String s1, String s2) {
        super("Oh dear! I think you might have made some mistakes in the date-time input.\n"
                + "The " + s1 + " is earlier than " + s2 + ".\n");
    }
}
