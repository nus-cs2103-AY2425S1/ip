public class InvalidScheduleException extends TalkaBotException {

    public InvalidScheduleException() {
        super("Sorry, what task were you trying to schedule again?");
    }
}
