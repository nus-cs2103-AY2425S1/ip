package joe.task;

import joe.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String serialize() {
        return String.format("T|%b|%s", this.isDone, this.description);
    }

    @Override
    public String toString () {
        return "[T]" + super.toString();
    }
}
