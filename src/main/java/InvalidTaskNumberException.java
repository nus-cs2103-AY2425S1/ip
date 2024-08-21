public class InvalidTaskNumberException extends NuggetException {
    public InvalidTaskNumberException() {
        super("OOPS!!! The task number you provided is invalid.");
    }
}