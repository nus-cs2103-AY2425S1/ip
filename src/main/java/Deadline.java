package src.main.java;

import src.main.java.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.by = deadline;
    }

    @Override
    public String getTaskInfo() {
        return "DEADLINE|" + String.valueOf(isDone ? 1 : 0) + "|" + this.description + "|" + this.by + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

}
