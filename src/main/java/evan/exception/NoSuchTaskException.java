package evan.exception;

/**
 * Represents an exception that is thrown when there is an attempt to perform an operation on a task that doesn't exist.
 */
public class NoSuchTaskException extends Exception {
    /**
     * Instantiates a NoSuchTaskException object.
     *
     * @param taskNumber Invalid task number that was being used.
     */
    public NoSuchTaskException(int taskNumber) {
        super("Oops! Task (" + taskNumber + ") does not exist!");
    }
}
