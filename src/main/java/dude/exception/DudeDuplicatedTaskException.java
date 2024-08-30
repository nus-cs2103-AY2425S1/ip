package dude.exception;

/**
 * Thrown to indicate that the user has attempted to add a task, but the task is already exists in TaskList.
 */
public class DudeDuplicatedTaskException extends DudeException {

    /**
     * Constructs a DudeDuplicatedTaskException with no detail message.
     */
    public DudeDuplicatedTaskException() {
        super("This task already exist.");
    }
}
