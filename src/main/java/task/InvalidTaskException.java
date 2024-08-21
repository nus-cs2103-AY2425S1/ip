package task;

public class InvalidTaskException extends Exception {
    public InvalidTaskException() {
        super("Invalid task.");
    }

    public InvalidTaskException(String message) {
        super(message);
    }
}