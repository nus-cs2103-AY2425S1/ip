package panorama.command;

/**
 * Represents the return result of an execute command of a {@code Command}.
 */
public class Response {
    private final String message;
    private final boolean isBye;

    /**
     * Constructor to initialize the message and the isBye flag.
     *
     * @param message The message output of the command.
     * @param isBye Indicates whether this message signals termination.
     */
    public Response(String message, boolean isBye) {
        this.message = message;
        this.isBye = isBye;
    }

    /**
     * Returns the message.
     *
     * @return the message string.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns whether the message signals termination.
     *
     * @return true if it signals termination, false otherwise.
     */
    public boolean isBye() {
        return isBye;
    }
}

