package nimbus.exception;

/**
 * Parent exception for all exceptions ran into in Nimbus
 */
public abstract class NimbusException extends RuntimeException {
    public NimbusException(String message) {
        super(message);
    }
}
