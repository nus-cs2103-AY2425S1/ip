package talkie.exception;

/**
 * Exception thrown when an unrecognized command is encountered.
 * <p>
 * The {@code TalkieUnknownCommandException} is used to indicate that the command provided by the user
 * is not recognized by the system.
 * </p>
 */
public class TalkieUnknownCommandException extends TalkieException {

    protected String command;

    /**
     * Constructs a {@code TalkieUnknownCommandException} with the specified command.
     *
     * @param command The command that was not recognized.
     */
    public TalkieUnknownCommandException(String command) {
        this.command = command;
    }

    /**
     * Returns a string representation of the exception.
     * <p>
     * This method provides a detailed message indicating that the command was not recognized,
     * along with a suggestion to try again.
     * </p>
     *
     * @return A string describing the exception.
     */
    @Override
    public String toString() {
        return "-------------------------------------------------------------------\n"
                + super.toString() + " I do not recognise the command, " + this.command +  ".\n"
                + "Please try again! :D\n"
                + "-------------------------------------------------------------------\n";
    }
}
