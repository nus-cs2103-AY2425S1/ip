package stan.tasks;

import stan.tasks.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
