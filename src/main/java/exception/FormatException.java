package exception;

/**
 * FormatException class is used to indicate format exceptions in D++E, 
 * particularly when the format for input to Ui is not suitable for task creation.
 */
public class FormatException extends DPlusPlusEException {
    /**
     * Constructor for FormatException.
     * @param taskType The type of task that the format is not suitable for.
     */
    public FormatException(String taskType) {
        super(String.format("The format for %s is not suitable.", taskType));
    }
}
