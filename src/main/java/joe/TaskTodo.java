package joe;
public class TaskTodo extends Task {
    public TaskTodo (String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveString() {
        return String.format("T|%d|%s", isDone() ? 1 : 0, getTask());
    }
}
