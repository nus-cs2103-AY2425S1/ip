package elon;

/**
 * Represents an exception encompassing all elon chatbot related errors
 */
public class ElonException extends Exception {
    /**
     * Constructs an ElonException receiving a specified error message.
     *
     * @param message the error message
     */
    public ElonException(String message) {
        super(message);
    }
}
