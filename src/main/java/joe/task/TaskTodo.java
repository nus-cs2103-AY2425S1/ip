package joe.task;

import static joe.Constants.TASK_TODO;

public class TaskTodo extends Task {
    public TaskTodo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TASK_TODO, super.toString());
    }

    @Override
    public String toSaveString() {
        return String.format("%s|%d|%s", TASK_TODO, isDone() ? 1 : 0, getTask());
    }
}
