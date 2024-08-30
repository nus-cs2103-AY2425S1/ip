package Exceptions;

/**
 * An exception that occurs when the specified task is not in the given taskList
 */
public class NotInTaskListException extends TestamentException {

    /**
     * Constructor for NotInTaskListException
     */
    public NotInTaskListException() {
        super("Apologies, but that task does not exist");
    }
}
