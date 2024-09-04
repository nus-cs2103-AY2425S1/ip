package alice.task;

/** For invalid task inputs. */
public class InvalidTaskException extends Exception {
    public InvalidTaskException() {
        super("Invalid task.");
    }

    public InvalidTaskException(String message) {
        super(message);
    }
}
