package kat.exceptions;

/**
 * Custom exception class for handling invalid messages in the chat bot application.
 */
public class InvalidMessageException extends Exception {

    /**
     * Constructs a new InvalidMessageException with the specified detail message.
     *
     * @param message The detail message
     */
    public InvalidMessageException(String message) {
        super(message);
    }

}
