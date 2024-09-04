package gopher.exception;

/**
 * Thrown if the input command has missing tokens.
 */
public class MissingTokenException extends Exception {
    private String taskType;
    private String missingToken;

    /**
     * Constructor for MissingTokenException class
     *
     * @param taskType type of the task in the invalid command
     * @param missingToken the missing token in the command
     */
    public MissingTokenException(String taskType, String missingToken) {
        super();
        this.taskType = taskType;
        this.missingToken = missingToken;
    }

    @Override
    public String getMessage() {
        return String.format("Seems like you are missing token %s when creating a %s\nPlease try again...",
                missingToken,
                taskType);
    }
}
