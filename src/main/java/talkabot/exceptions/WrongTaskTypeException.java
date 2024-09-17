package talkabot.exceptions;

/**
 * WrongTaskTypeException class to signal wrong type of task given by the user.
 */
public class WrongTaskTypeException extends TalkaBotException {

    /**
     * Constructor for WrongTaskTypeException class.
     */
    public WrongTaskTypeException(String message) {
        super("Sorry, this task " + message);
    }
}
