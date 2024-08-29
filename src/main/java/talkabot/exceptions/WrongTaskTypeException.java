package talkabot.exceptions;

public class WrongTaskTypeException extends TalkaBotException {

    public WrongTaskTypeException(String message) {
        super("Sorry, this talkabot.task " + message);
    }
}
