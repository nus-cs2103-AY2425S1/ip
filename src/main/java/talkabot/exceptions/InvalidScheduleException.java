package talkabot.exceptions;

/**
 * InvalidScheduleException class to signal wrong task input given by the user.
 */
public class InvalidScheduleException extends TalkaBotException {

    /**
     * Constructor for InvalidScheduleException class.
     */
    public InvalidScheduleException() {
        super("Sorry, what task were you trying to schedule again?");
    }
}
