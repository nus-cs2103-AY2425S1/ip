package exception;

/**
 * Exception that thrown by Parser
 */
public class ParserException extends ToMoException {
    /**
     * Constructor of StorageException
     * 
     * @param message
     */
    public ParserException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ParserException: " + getMessage();
    }
}
