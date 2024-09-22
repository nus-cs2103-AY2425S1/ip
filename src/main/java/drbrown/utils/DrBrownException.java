package drbrown.utils;

/**
 * Represents an exception specific to the DrBrown application.
 * This exception is used to indicate errors that occur during the execution of the program.
 */
public class DrBrownException extends Exception {

    /**
     * Constructs a new DrBrownException with the specified detail message.
     *
     * @param message The detail message for the exception.
     */
    public DrBrownException(String message) {
        super(message);
        assert message != null : "Exception message should not be null";
    }
}
