public class TaskNotFoundException extends GladosException {
    public TaskNotFoundException() {
        super("Task does not exist.");
    }
}