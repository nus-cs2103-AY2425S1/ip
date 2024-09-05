package stobberi.stobberiexception;

/**
 * Custom exception for cases where a Stobberi duration is not possible.
 *
 * <p>This exception extends {@link StobberiException} and indicates that
 * an operation failed due to an invalid or impossible duration for a Stobberi object.</p>
 */
public class NotPossibleDurationStobberiException extends StobberiException {

    /**
     * Constructs a new NotPossibleDurationStobberiException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by
     *                the {@link #getMessage()} method.
     */
    public NotPossibleDurationStobberiException(String message) {
        super(message);
    }
}
