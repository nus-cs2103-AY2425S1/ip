public class WrongTaskTypeException extends TalkaBotException {

    public WrongTaskTypeException(String message) {
        super("Sorry, this task " + message);
    }
}
