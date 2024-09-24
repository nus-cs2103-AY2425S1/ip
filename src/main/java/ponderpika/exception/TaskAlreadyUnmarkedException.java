package ponderpika.exception;

/**
 * Exception thrown when an attempt is made to unmark a task that is already in unmarked state
 * (task which is still not done yet)
 * <p>
 * This class extends PonderPikaException to handle cases where a task's status
 * is already in unmarked state.
 * </p>
 */
public class TaskAlreadyUnmarkedException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that the task is not done yet.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Task has not been done yet (still unmarked)!");
    }
}
