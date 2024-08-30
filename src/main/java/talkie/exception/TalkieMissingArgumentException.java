package talkie.exception;

/**
 * Represents an exception thrown when a command is missing required arguments in the Talkie application.
 * <p>
 * {@code TalkieMissingArgumentException} extends {@code TalkieException} and is used to indicate that
 * a required argument for a command is missing. It also provides a hint for resolving the issue.
 * </p>
 */
public class TalkieMissingArgumentException extends TalkieException {

    /** The command that is missing required arguments. */
    protected String command;

    /** A hint for correcting the missing arguments. */
    protected String hint;

    /**
     * Constructs a {@code TalkieMissingArgumentException} with the specified command and hint.
     *
     * @param command The command with missing arguments.
     * @param hint A hint for correcting the missing arguments.
     */
    public TalkieMissingArgumentException(String command, String hint) {
        this.command = command;
        this.hint = hint;
    }

    /**
     * Returns a string representation of the exception.
     * <p>
     * This method provides a detailed message including the command with missing arguments, a hint for correction,
     * and a suggestion to try again.
     * </p>
     *
     * @return A string representing the exception message.
     */
    @Override
    public String toString() {
        return "-------------------------------------------------------------------\n"
                + super.toString() + " Your " + this.command + " has missing arguments!\n"
                + "Hint: " + hint +"\n"
                + "Please try again! :D\n"
                + "-------------------------------------------------------------------\n";
    }
}
