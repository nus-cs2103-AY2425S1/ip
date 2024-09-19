package slave;

/**
 * This exception is thrown when the user does not provide all the required information regarding the task
 */
public class InvalidTaskFormatException extends Exception {
    public InvalidTaskFormatException(String error) {
        super(error);
    }
}
