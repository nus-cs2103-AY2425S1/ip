package exception;

/**
 * Exception relates to the chatbot
 */
public class ToMoException extends Exception {
    /**
     * Constructor for the exception
     * 
     * @param message The error message
     */
    public ToMoException(String message) {
        super(message);
    }
}
