package totoro.exception;

/**
 * Represents an exception that is thrown when an invalid task number is provided
 * in the Totoro chatbot. This occurs when a task number does not correspond to
 * an existing task in the system
 * <p>
 *     This exception helps inform users about incorrect task number inputs, allowing
 *     them to correct their command accordingly
 * </p>
 */
public class TotoroInvalidTaskNumberException extends TotoroException {
    private int taskNumber;

    public TotoroInvalidTaskNumberException(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String toString() {
        return String.format("%s Task number %d is invalid", super.toString(), this.taskNumber + 1);
    }
}
