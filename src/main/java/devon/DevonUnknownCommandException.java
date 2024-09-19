package devon;

/**
 * Exception thrown when an unknown or unrecognized command is encountered in the Devon application.
 * This exception is a specific type of {@link DevonException} used to indicate errors
 * related to invalid commands input by the user.
 */
public class DevonUnknownCommandException extends DevonException {

    /**
     * Returns a detailed string representation of the exception, including the specific
     * error message related to unknown commands.
     *
     * @return A string indicating the error: "OOPS!!! Unknown command!".
     */
    @Override
    public String toString() {
        return super.toString() + " Unknown command!";
    }
}
