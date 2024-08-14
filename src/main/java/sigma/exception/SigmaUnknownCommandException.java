package sigma.exception;

/**
 * Thrown when an unknown command is entered in the Sigma application.
 */
public class SigmaUnknownCommandException extends SigmaException {
    private String command;

    /**
     * Constructs a SigmaUnknownCommandException with the specified unknown command.
     *
     * @param command the unknown command entered by the user
     */
    public SigmaUnknownCommandException(String command) {
        this.command = command;
    }

    /**
     * Returns a detailed error message indicating the unknown command.
     *
     * @return a string representing the error message with the unknown command
     */
    @Override
    public String toString() {
        return String.format("%s Unknown command: %s", super.toString(), this.command);
    }
}
