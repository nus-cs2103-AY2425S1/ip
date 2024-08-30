package talkie.exception;

/**
 * Represents an exception thrown when an invalid argument is provided for a command in the Talkie application.
 * <p>
 * {@code TalkieInvalidArgumentException} extends {@code TalkieException} and is used to indicate that
 * the provided arguments for a command are invalid. It also provides a hint for resolving the issue.
 * </p>
 */
public class TalkieInvalidArgumentException extends TalkieException {

    /** The command that had invalid arguments. */
    protected String command;

    /** A hint for correcting the invalid arguments. */
    protected String hint;

    /**
     * Constructs a {@code TalkieInvalidArgumentException} with the specified command and hint.
     *
     * @param command The command with invalid arguments.
     * @param hint A hint for correcting the invalid arguments.
     */
    public TalkieInvalidArgumentException(String command, String hint) {
        this.command = command;
        this.hint = hint;
    }

    /**
     * Returns a string representation of the exception.
     * <p>
     * This method provides a detailed message including the invalid command, a hint for correction,
     * and a suggestion to try again.
     * </p>
     *
     * @return A string representing the exception message.
     */
    @Override
    public String toString() {
        return "-------------------------------------------------------------------\n"
                + super.toString() + " Your " + this.command + " has invalid arguments!\n"
                + "Hint: " + hint +"\n"
                + "Please try again! :D\n"
                + "-------------------------------------------------------------------\n";
    }
}
