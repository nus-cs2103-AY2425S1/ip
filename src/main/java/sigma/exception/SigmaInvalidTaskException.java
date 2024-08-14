package sigma.exception;

/**
 * Thrown when an invalid task number is provided in the Sigma application.
 */
public class SigmaInvalidTaskException extends SigmaException {
    private int taskNumber;

    /**
     * Constructs a SigmaInvalidTaskException with the specified task number.
     *
     * @param taskNumber the task number that is invalid
     */
    public SigmaInvalidTaskException(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Returns a detailed error message indicating the invalid task number.
     *
     * @return a string representing the error message with the invalid task number
     */
    @Override
    public String toString() {
        return String.format("%s sigma.task.Task number %d is invalid.", super.toString(), this.taskNumber);
    }
}
