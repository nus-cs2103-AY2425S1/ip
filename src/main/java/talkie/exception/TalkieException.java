package talkie.exception;

/**
 * The base class for all exceptions in the Talkie application.
 * <p>
 * {@code TalkieException} serves as the root of the exception hierarchy for the Talkie application.
 * It extends {@code Exception} and provides a custom string representation for exceptions in this application.
 * </p>
 */
public abstract class TalkieException extends Exception {

    /**
     * Returns a string representation of the exception.
     * <p>
     * This method provides a generic message indicating that an error has occurred.
     * </p>
     *
     * @return A string representing the exception message.
     */
    @Override
    public String toString() {
        return "OOPS!";
    }
}
