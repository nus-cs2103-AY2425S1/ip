package exceptions;

public class TaskDoesNotExistException extends Exception {
    public TaskDoesNotExistException(int index) {
        super("task " + (index + 1) + " does not exist");
    }
}
