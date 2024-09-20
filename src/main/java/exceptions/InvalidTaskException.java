package exceptions;

public class InvalidTaskException extends Throwable {
    public InvalidTaskException(int index) {
        super("OOPS!!! Task " + (index + 1) + " does not exist.\n");
    }
}
