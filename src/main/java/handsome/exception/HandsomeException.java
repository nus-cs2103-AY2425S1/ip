package handsome.exception;

/**
 * The HandsomeException class is a custom exception used in the Handsome
 * chatBot application to handle errors specific to the application's operations.
 * This class serves as the base exception for various custom exceptions
 * thrown by the Handsome chatBot, providing a mechanism to relay specific error messages.
 */
public class HandsomeException extends Exception {
    public HandsomeException(String msg) {
        super(msg);
    }
}
