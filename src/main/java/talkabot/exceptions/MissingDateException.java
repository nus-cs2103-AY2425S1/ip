package talkabot.exceptions;

/**
 * MissingDateException class to signal missing date for adding deadlines and events.
 */
public class MissingDateException extends TalkaBotException {

    /**
     * Constructs an instance of MissingDateException class.
     */
    public MissingDateException(String detail) {
        super("Could you let me know what the " + detail + " is so I can record it for you?");
    }
}
