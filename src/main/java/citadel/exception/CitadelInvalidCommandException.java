package citadel.exception;

/**
 * Represents an exception that is thrown when the user inputs an invalid command.
 * This exception extends {@link CitadelException}.
 */
public class CitadelInvalidCommandException extends CitadelException {

    /**
     * Returns a string representation of this exception, which includes a message
     * indicating that the command entered is invalid.
     *
     * @return A string message indicating the command is invalid.
     */
    @Override
    public String toString() {
        return super.toString()
                + "Command is invalid :(";
    }
}
