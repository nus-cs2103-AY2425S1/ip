package bimo.exception;

/**
 * Represents Exception when date entered is not in yyyy-mm-dd format.
 */
public class InvalidDateFormatException extends BimoException {
    /**
     * Instantiates InvalidDateFormatException.
     */
    public InvalidDateFormatException() {
        super("Unable to get date, please use yyyy-mm-dd as date format ");
    }
}
