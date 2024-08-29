package main.java.angel;

/**
 * Exception thrown when a specified task is not found.
 * This class extends the {@link AngelException} class.
 */
public class TaskNotFoundException extends AngelException {

    /**
     * Constructs a new TaskNotFoundException with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}
