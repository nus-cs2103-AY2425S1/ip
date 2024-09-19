package talkabot.exceptions;

public class MissingDetailException extends TalkaBotException {

    /**
     * Constructs an instance of UnknownTimeException class.
     */
    public MissingDetailException(String detail) {
        super("Could you let me know what the " + detail + " is so I can record it for you?");
    }
}
