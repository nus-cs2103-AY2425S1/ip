package sammy;

public class InvalidTaskNumberException extends SammyException {
    public InvalidTaskNumberException() {
        super("The task number provided is invalid.");
    }
}