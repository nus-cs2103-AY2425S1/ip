package talkabot.exceptions;

/**
 * InvalidScheduleException class to signal wrong task input given by the user.
 */
public class InvalidScheduleException extends TalkaBotException {

    /**
     * Constructs an instance of InvalidScheduleException class.
     */
    public InvalidScheduleException() {
        super("Sorry pal, what task were you trying to"
                + "schedule again?");
    }
}
