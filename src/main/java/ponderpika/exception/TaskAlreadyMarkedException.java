package ponderpika.exception;

/**
 * Exception thrown when an attempt is made to mark a task that has already been marked as done.
 * <p>
 * This class extends PonderPikaException to handle cases where a task's status
 * is already completed.
 * </p>
 */
public class TaskAlreadyMarkedException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that the task has already been marked as done.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Task has already been done (marked)!");
    }
}
