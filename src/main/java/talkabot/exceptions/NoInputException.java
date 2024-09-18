package talkabot.exceptions;

/**
 * NoInputException class to signal absence of input given by the user.
 */
public class NoInputException extends TalkaBotException {

    /**
     * Constructs an instance of NoInputException class.
     */
    public NoInputException() {
        super("Oh no! Did you forget what you were going to say?");
    }

}
