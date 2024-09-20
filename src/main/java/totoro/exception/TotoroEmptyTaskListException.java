package totoro.exception;

/**
 * Represents an exception thrown when an operation is attempted on an empty task list
 * This is a specific type of {@link TotoroException} that indicates that there are no tasks in the list
 */
public class TotoroEmptyTaskListException extends TotoroException {
    @Override
    public String toString() {
        return String.format("%s There are no tasks at the moment", super.toString());
    }
}
