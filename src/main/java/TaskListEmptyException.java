public class TaskListEmptyException extends BottyException {
    public TaskListEmptyException() {
        super("Your list is empty! Add a task with the todo, deadline or event command.");
    }
}
