package Exceptions;

/**
 * An exception that occurs when the task description is empty
 */
public class TaskDescriptionEmptyException extends TestamentException {

    /**
     * Creates a TaskDescriptionEmptyException for the specified type of Task
     *
     * @param s Type of Task
     */
    public TaskDescriptionEmptyException(String s) {
        super(String.format("It appears that you have not provided any details for this %s.",
                s));
    }

}
