package stobberi.stobberiexception;

/**
 * Custom exception for cases where a Stobberi object is empty.
 *
 * <p>This exception extends {@link StobberiException} and is used to indicate
 * that an operation failed due to an empty or uninitialized Stobberi object.</p>
 */
public class EmptyStobberiException extends StobberiException {

    /**
     * Constructs a new EmptyStobberiException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by
     *                the {@link #getMessage()} method.
     */
    public EmptyStobberiException(String message) {
        super(message);
    }
}
