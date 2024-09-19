package dude.exception;

/**
 * Thrown to indicate that no task with matching prompt is found in TaskList.
 */
public class DudeTaskNotFoundException extends DudeException {

    /**
     * Constructs a DudeTaskNotFoundException with no detail message.
     */
    public DudeTaskNotFoundException() {
        super("Am I tripping, or are you looking for something that doesn't exist?");
    }
}
