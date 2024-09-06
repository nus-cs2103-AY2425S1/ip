package stobberi.stobberiexception;

/**
 * Custom exception for cases where a Stobberi object lacks a number.
 *
 * <p>This exception extends {@link StobberiException} and indicates that
 * an operation failed because the Stobberi object does not contain a number.</p>
 */
public class InvalidNumberStobberiException extends StobberiException {

    /**
     * Constructs a new NoNumberStobberiException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by
     *                the {@link #getMessage()} method.
     */
    public InvalidNumberStobberiException(String message) {
        super(message);
    }
}
