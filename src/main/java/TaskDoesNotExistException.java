public class TaskDoesNotExistException extends Exception {
    public TaskDoesNotExistException(int index) {
        super("Task " + (index + 1) + " does not exist");
    }
}
