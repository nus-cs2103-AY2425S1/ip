package talkabot.exceptions;

/**
 * NoInputException class to signal absence of input given by the user.
 */
public class NoInputException extends TalkaBotException {

    /**
     * Constructor for NoInputException class.
     */
    public NoInputException() {
        super("Oh no! Did you forget what you were going to say?");
    }

}
