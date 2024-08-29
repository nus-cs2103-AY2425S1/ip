package neuro.task;

import neuro.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveData() {
        return "T | " + super.toSaveData();
    }
}
