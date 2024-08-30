package dudu.task;

import dudu.task.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String formatString() {
        return String.format("T | %s", super.formatString());
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
