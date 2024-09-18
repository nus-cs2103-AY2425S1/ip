package cypherchatbot;

/**
 * Custom exception class for handling errors in the Cypher Chatbot application.
 */
public class CypherException extends Exception {
    private String message;

    /**
     * Constructs a new CypherException with a given message.
     *
     * @param msg given message which explains the reason for the exception
     */
    public CypherException(String msg) {
        super(msg);
        this.message = msg;
    }

    /**
     * Retrieves the message associated with this exception.
     *
     * @return the error message provided when the exception was thrown
     */
    public String getMessage() {
        return this.message;
    }
}
