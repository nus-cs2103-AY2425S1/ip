package janet;

/**
 * Represents an exception that has unique messages to Janet.
 */
public class JanetException extends Exception {

    public JanetException(String errorMessage) {
        super(errorMessage);
    }
}
