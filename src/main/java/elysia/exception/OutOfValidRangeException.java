package elysia.exception;

/**
 * Throws an exception if the index is not valid (index < 0 or index >= size of task list).
 */
public class OutOfValidRangeException extends ElysiaException {
    /**
     * Constructs a new {@code OutOfValidRangeException} with an error message.
     * This message indicates that the provided index is invalid and should be
     * a valid positive index within the allowed range.
     */
    public OutOfValidRangeException() {
        super("Oh no! Please enter a valid positive index within the range.\n");
    }
}
