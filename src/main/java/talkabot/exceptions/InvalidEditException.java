package talkabot.exceptions;

/**
 * InvalidEditException class to signal wrong task input given by the user to edit.
 */
public class InvalidEditException extends TalkaBotException {

    /**
     * Constructs an instance of InvalidEditException class.
     */
    public InvalidEditException(String edit) {
        super("Sorry, what were you trying to " + edit + " again?");
    }
}
