package lawrence.task;

import lawrence.task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    public String toSaveFormat() {
        return String.format("T | %s", super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
