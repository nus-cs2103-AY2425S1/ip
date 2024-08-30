package dude.exception;

/**
 * Thrown to indicate that no task with matching prompt is found in TaskList.
 */
public class DudeTaskNotFoundException extends DudeException {

    /**
     * Constructs a DudeTaskNotFoundException with no detail message.
     */
    public DudeTaskNotFoundException() {
        super("No matching task found in your task list.");
    }
}
