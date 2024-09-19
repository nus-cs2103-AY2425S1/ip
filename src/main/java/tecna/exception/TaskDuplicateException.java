package tecna.exception;

/**
 * Indicates duplicate tasks error when adding a new task.
 *
 * @author DennieDan.
 */
public class TaskDuplicateException extends Exception{
    /**
     * Constructs a TaskDuplicateException instance.
     */
    public TaskDuplicateException(){
        super("This task already exists.");
    }
}
