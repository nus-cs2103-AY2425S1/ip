package bibi.exception;

/**
 * Represents the parent class of BibiRuntimeExceptions.
 */
public class BibiRuntimeException extends RuntimeException {
    public BibiRuntimeException(String message) {
        super(message);
    }
}
