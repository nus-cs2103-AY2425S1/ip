package talkabot.exceptions;

public class InvalidScheduleException extends TalkaBotException {

    public InvalidScheduleException() {
        super("Sorry, what talkabot.task were you trying to schedule again?");
    }
}
