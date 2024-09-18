package stobberi.stobberiexception;

/**
 * Custom exception for cases where a Stobberi object has wrong input.
 *
 * <p>This exception extends {@link StobberiException} and indicates that
 * an operation failed because command input is not what is expected.</p>
 */
public class InvalidInputStobberiException extends StobberiException {

    /**
     * Constructs a new InvalidInputStobberiException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by
     *                the {@link #getMessage()} method.
     */
    public InvalidInputStobberiException(String message) {
        super(message);
    }
}
