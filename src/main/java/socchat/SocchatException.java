package socchat;

/**
 * The SocchatException class represents a custom exception used in the Socchat application.
 * It extends the standard Exception class and allows for specific error messages to be passed.
 */
public class SocchatException extends Exception {

    /**
     * Constructs a new SocchatException with the specified error message.
     *
     * @param message the detail message providing more information about the error
     */
    public SocchatException(String message) {
        super(message);
    }

}
