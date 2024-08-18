public class InvalidTaskIndexException extends BrunoException {
    public InvalidTaskIndexException() {
        super("The task number is invalid, try the command 'list' first");
    }
}
