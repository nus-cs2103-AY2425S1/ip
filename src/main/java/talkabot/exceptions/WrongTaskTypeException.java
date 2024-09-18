package talkabot.exceptions;

/**
 * WrongTaskTypeException class to signal wrong type of task given by the user.
 */
public class WrongTaskTypeException extends TalkaBotException {

    /**
     * Constructs an instance of WrongTaskTypeException class.
     */
    public WrongTaskTypeException(String message) {
        super("Sorry pal, this task " + message);
    }
}
