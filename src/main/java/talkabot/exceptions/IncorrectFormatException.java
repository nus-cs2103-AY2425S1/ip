package talkabot.exceptions;

/**
 * IncorrectFormatException class to signal wrong format for adding deadlines and events.
 */
public class IncorrectFormatException extends TalkaBotException {

    /**
     * Constructs an instance of IncorrectFormatException class.
     */
    public IncorrectFormatException() {
        super("Hey buddy, remember to leave a space before the '/'s!");
    }
}
