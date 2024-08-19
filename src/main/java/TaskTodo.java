public class TaskTodo extends Task {
    public TaskTodo (String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
