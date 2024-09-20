package dude.exception;

/**
 * Thrown to indicate that no date and time is provided in the input for specific command.
 */
public class DudeNullDateTimeException extends DudeException {

    /**
     * Constructs a new DudeNullDateTimeException with a detailed message
     * indicating that a date and time are required for the specified command.
     *
     * @param command The name of the command that requires a date and time.
     */
    public DudeNullDateTimeException(String command) {
        super("Can't you just tell me the date and time for \"" + command + "\"?");
    }
}
