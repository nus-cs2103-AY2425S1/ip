package ponderpika.exception;

/**
 * Exception thrown when a search for tasks yields no matching result from the list of tasks
 * <p>
 * This class extends PonderPikaException to handle cases where the specified keyword does not match any tasks.
 * </p>
 */
public class NoMatchingTasksFoundException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that no matching tasks were found.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "No Matching tasks found in the list!");
    }
}
