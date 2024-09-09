package yapper.exception;

/**
 * Represents an exception that occurs when the user provides an invalid task number.
 */
public class InvalidTaskNumberException extends YapperException {
    /**
     * Constructs an InvalidTaskNumberException with the specified task number.
     *
     * @param taskNumber The invalid task number provided by the user.
     */
    public InvalidTaskNumberException(int taskNumber) {
        super("Hello boss your task number " + (taskNumber + 1) + " is not valid. Please provide a valid task number.");
    }
}
