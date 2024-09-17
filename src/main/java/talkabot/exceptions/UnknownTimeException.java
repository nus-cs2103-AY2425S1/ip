package talkabot.exceptions;

/**
 * UnknownTimeException class to signal absence of time input for deadlines/events.
 */
public class UnknownTimeException extends TalkaBotException {

    /**
     * Constructor for UnknownTimeException class.
     */
    public UnknownTimeException(String action) {
        super("Please let me know when it " + action + " so I can record it for you!");
    }
}
