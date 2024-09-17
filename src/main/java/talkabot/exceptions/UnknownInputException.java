package talkabot.exceptions;

/**
 * UnknownInputException class to signal unknown input by the user.
 */
public class UnknownInputException extends TalkaBotException {

    /**
     * Constructor for UnknownInputException class.
     */
    public UnknownInputException(String str) {
        super("\"" + str + "\"...? What does that mean?");
    }
}
