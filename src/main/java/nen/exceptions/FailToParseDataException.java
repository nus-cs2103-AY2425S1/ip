package nen.exceptions;

/**
 * This class represent an exception which is thrown when the data read from data file has wrong format
 * @author Gan Ren Yick (A0276246X)
 */

public class FailToParseDataException extends Exception {
    public FailToParseDataException(String message) {
        super(message);
    }
}
