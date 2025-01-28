package Azir;

/**
 * Represents chatbot errors
 */
public class AzirException extends Exception{
    /**
     * Constructs a new AzirException with the error message.
     *
     * @param message Error message
     */
    public AzirException(String message) {
        super(message);
    }
}