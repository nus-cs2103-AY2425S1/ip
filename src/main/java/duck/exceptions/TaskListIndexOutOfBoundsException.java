package duck.exceptions;

public class TaskListIndexOutOfBoundsException extends Exception {
    private int indexSupplied;
    private int maxIndexAllowed;
    private UsageException usageException;

    public TaskListIndexOutOfBoundsException(int indexSupplied, int maxIndexAllowed, UsageException usageException) {
        this.indexSupplied = indexSupplied;
        this.maxIndexAllowed = maxIndexAllowed;
        this.usageException = usageException;
    }

    @Override
    public String toString() {
        return String.format("%s\nIndex %s is out of bounds. Index should be an integer from 1 to %s (inclusive).",
                usageException.toString(), indexSupplied, maxIndexAllowed);
    }
}
