package talkabot.exceptions;

/**
 * NoInputException class to signal absence of input given by the user.
 */
public class NoInputException extends TalkaBotException {

    /**
     * Constructs an instance of NoInputException class.
     */
    public NoInputException() {
        super("I'm afraid I did not catch what you said, pal!");
    }

}
