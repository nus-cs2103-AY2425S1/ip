package bobby.exceptions;

public class InvalidTaskNumberException extends BobbyException {
    public InvalidTaskNumberException() {
        super("The task number provided is invalid.");
    }
}
