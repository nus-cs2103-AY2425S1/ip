package ponderpika.exception;

/**
 * Exception thrown when a task is issued without specifying a priority level in a Pika context.
 * <p>
 * This class extends PonderPikaException to handle cases where a required priority level is missing for a task.
 * </p>
 */
public class MissingPriorityLevelException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that the priority level is not mentioned for the task.
     *
     * @return A string that represents the exception, including the
     *     superclass message and details about the missing priority level.
     */
    @Override
    public String toString() {
        return (super.toString() + "Priority level not mentioned for the task!");
    }
}
