package talkabot.exceptions;

public class IncorrectFormatException extends TalkaBotException {

    /**
     * Constructs an instance of InvalidEditException class.
     */
    public IncorrectFormatException() {
        super("Hey buddy, remember to leave a space before the '/'s!");
    }
}
