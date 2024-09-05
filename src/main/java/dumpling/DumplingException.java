package dumpling;

/**
 * Exception class where the message contains the information for different issues
 */
public class DumplingException extends RuntimeException {
    public DumplingException(String message) {
        super(message);
    }
}
