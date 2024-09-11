package yoda.exceptions;

/**
 * Represents a general exception for the chatbot.
 */
public class YodaException extends Exception {
    /**
     * Constructs a new YodaException with the specified detail message.
     *
     * @param s The detail message which is saved for later retrieval.
     */
    public YodaException(String s) {
        super(s);
    }

}
