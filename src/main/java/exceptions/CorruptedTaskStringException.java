package exceptions;

public class CorruptedTaskStringException extends BottyException {
    public CorruptedTaskStringException() {
        super("Task string is corrupted! Failed to retrieve task");
    }
}
