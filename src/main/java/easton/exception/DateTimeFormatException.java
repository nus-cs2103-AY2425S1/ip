package easton.exception;

/**
 * Exception for incorrect date & time format.
 */
public class DateTimeFormatException extends Exception {
    /**
     * Constructs a new exception with a generated detail message.
     */
    public DateTimeFormatException() {
        super("Wrong DateTime Format!!! Please use the format, d/M/yyyy H:mm (e.g. 29/12/2024 12:00).");
    }
}
