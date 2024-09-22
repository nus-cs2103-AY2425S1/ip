package elysia.exception;

/**
 * Represents an invalid date format exception.
 */
public class InvalidDateTimeFormatException extends ElysiaException {
    /**
     * Constructs an {@code InvalidDateTimeFormatException} with a message indicating that the time format is invalid.
     */
    public InvalidDateTimeFormatException() {
        super("Oh my! I'm so sorry,\n"
                + "but it seems the time format is invalid.\n"
                + "Let's use the valid format such as:\n"
                + "2:30PM (h:mma)\n"
                + "2:30 PM (h:mm a)\n"
                + "1430 (HHmm)\n"
                + "14:30 (HH:mm)\n"
                + "2PM (ha)\n"
                + "2 PM (h a)\n");
    }

    /**
     * Constructs an {@code InvalidDateTimeFormatException} with a message indicating that the date-time format is
     * invalid.
     *
     * @param s
     */
    public InvalidDateTimeFormatException(String s) {
        super("Oh my! I'm so sorry,\n"
                + "but it seems the date-time format is invalid.\n"
                + "Let's use the valid format such as:\n"
                + "2024-09-23\\2:30PM (h:mma)\n"
                + "2024-09-23\\2:30 PM (h:mm a)\n"
                + "2024-09-23\\1430 (HHmm)\n"
                + "2024-09-23\\14:30 (HH:mm)\n"
                + "2024-09-23\\2PM (ha)\n"
                + "2024-09-23\\2 PM (h a)\n");
    }
}
