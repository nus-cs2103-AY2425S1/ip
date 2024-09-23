package elysia.exception;

public class OutOfValidRangeException extends ElysiaException {
    public OutOfValidRangeException() {
        super("Oh no! Please enter a valid positive index within the range.\n");
    }
}
