package totoro.exception;

/**
 * Represents an exception that is thrown when an unrecognised command is
 * encountered in the Totoro chatbot. This exception indicates that the
 * provided command does not match any known commands
 * <p>
 *     This helps users understand that their input was not valid and prompts
 *     them to check their commands for accuracy
 * </p>
 */
public class TotoroUnknownCommandException extends TotoroException {
    private String command;

    public TotoroUnknownCommandException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("%s I don't know what %s means :(", super.toString(), this.command);
    }
}
