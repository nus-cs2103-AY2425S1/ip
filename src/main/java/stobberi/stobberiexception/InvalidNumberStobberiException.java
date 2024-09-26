package stobberi.stobberiexception;

/**
 * Custom exception for cases where the number is not valid.
 *
 * <p>This exception extends {@link StobberiException} and indicates that
 * an operation failed because the number is too big or too small.</p>
 */
public class InvalidNumberStobberiException extends StobberiException {

    /**
     * Constructs a new InvalidNumberStobberiException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by
     *                the {@link #getMessage()} method.
     */
    public InvalidNumberStobberiException(String message) {
        super(message);
    }
}
