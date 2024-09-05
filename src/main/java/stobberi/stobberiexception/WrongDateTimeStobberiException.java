package stobberi.stobberiexception;

/**
 * Custom exception for cases where a Stobberi date-time is incorrect.
 *
 * <p>This exception extends {@link StobberiException} and indicates that
 * an operation failed due to an invalid or incorrect date-time value in a Stobberi object.</p>
 */
public class WrongDateTimeStobberiException extends StobberiException {

    /**
     * Constructs a new WrongDateTimeStobberiException with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by
     *                the {@link #getMessage()} method.
     */
    public WrongDateTimeStobberiException(String message) {
        super(message);
    }
}
