package talkabot.exceptions;

/**
 * UnknownTimeException class to signal absence of time input for deadlines/events.
 */
public class UnknownTimeException extends TalkaBotException {

    /**
     * Constructs an instance of UnknownTimeException class.
     */
    public UnknownTimeException(String action) {
        super("Could you let me know when it " + action + " so I can record it for you?");
    }
}
