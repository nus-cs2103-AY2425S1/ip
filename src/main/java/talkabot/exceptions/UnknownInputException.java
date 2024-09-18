package talkabot.exceptions;

/**
 * UnknownInputException class to signal unknown input by the user.
 */
public class UnknownInputException extends TalkaBotException {

    /**
     * Constructs an instance of UnknownInputException class.
     */
    public UnknownInputException() {
        super("Could you explain that a bit more, pal?");
    }
}
