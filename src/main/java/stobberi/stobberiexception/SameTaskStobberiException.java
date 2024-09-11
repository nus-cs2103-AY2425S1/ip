package stobberi.stobberiexception;

/**
 * Custom exception for cases where a Stobberi object is empty.
 *
 * <p>This exception extends {@link StobberiException} and is used to indicate
 * that an operation failed due to a repeated Task object.</p>
 */
public class SameTaskStobberiException extends StobberiException {

    /**
     * Constructs a new SameTaskStobberiException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by
     *                the {@link #getMessage()} method.
     */
    public SameTaskStobberiException(String message) {
        super(message);
    }
}
