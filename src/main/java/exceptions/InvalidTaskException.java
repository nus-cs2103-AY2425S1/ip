package exceptions;

public class InvalidTaskException extends Exception {
    public InvalidTaskException() {
        super("Invalid Task Type.");
    }
}
