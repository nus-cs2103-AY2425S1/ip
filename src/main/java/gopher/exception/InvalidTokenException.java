package gopher.exception;

/**
 * Thrown if the given command contains tokens that cannot be accepted
 * by the task that is involved in the operation.
 */
public class InvalidTokenException extends Exception {
    private String taskType;
    private String token;

    /**
     * Constructor for InvalidTokenException.
     *
     * @param taskType type of task involved in the operation
     * @param token token that triggers the exception
     */
    public InvalidTokenException(String taskType, String token) {
        this.taskType = taskType;
        this.token = token;
    }

    @Override
    public String getMessage() {
        return String.format("Sorry, %s task cannot accept %s token as part of command\n"
                        + "Please try again...\n",
                this.taskType,
                this.token);
    }
}
