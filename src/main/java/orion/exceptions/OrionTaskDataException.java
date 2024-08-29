package orion.exceptions;

public class OrionTaskDataException extends Exception {
    // Existing task list corrupted

    public OrionTaskDataException(String message) {
        super(message);
    }
}
