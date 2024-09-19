package nen.exceptions;

/**
 * This class represent an exception which is thrown when the argument(s) of a task(date and time) has incorrect format
 * @author Gan Ren Yick (A0276246X)
 */

public class DateTimeFormatIncorrectException extends Exception {
    public DateTimeFormatIncorrectException(String message) {
        super(message);
    }
}
