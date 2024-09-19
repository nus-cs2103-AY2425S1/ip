package exceptions;

/**
 * Denotes a specific user input error, where the user failed to provide a date in yyyy-mm-dd ISO format
 */
public class InvalidIsoFormatException extends GrokInvalidUserInputException {
    public InvalidIsoFormatException() {
        super("Invalid date format. Please declare your date in the ISO format yyyy-mm-dd.");
    }
}
