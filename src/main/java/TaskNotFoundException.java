public class TaskNotFoundException extends GladosException {
    public TaskNotFoundException() {
        super("GLaDOS: Task does not exist in the list.");
    }
}