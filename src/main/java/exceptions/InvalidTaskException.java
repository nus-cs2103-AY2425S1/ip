package exceptions;

/**
 * Represents exceptions thrown by the chatbot.
 * Occurs when invalid task number is specified.
 */
public class InvalidTaskException extends AliceException {
    protected int taskNumber;

    /**
     * Initialises an InvalidTaskException.
     *
     * @param message the error message.
     * @param taskNumber the taskNumber of the task that did not exist.
     */
    public InvalidTaskException(String message, int taskNumber) {
        super(message);
        this.taskNumber = taskNumber;
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return message that task does not exist.
     */
    @Override
    public String toString() {
        return String.format("Task %d does not exist!", this.taskNumber);
    }
}
