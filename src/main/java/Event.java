package src.main.java;

import src.main.java.Task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskInfo() {
        return "EVENT|" + String.valueOf(isDone ? 1 : 0) + "|" + this.description + "|" + this.from + "|" +
                this.to + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
