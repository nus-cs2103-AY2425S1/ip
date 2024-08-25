package Neko.Task;

import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private boolean isDone;

    protected static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("eee, d MMM uuuu h:mma");

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean markAsDone() {
        if (this.isDone) return false;
        this.isDone = true;
        return true;
    }

    public boolean markAsNotDone() {
        if (!this.isDone) return false;
        this.isDone = false;
        return true;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public String getTime() { return ""; };

    public String getDescription() { return this.name; }

    public boolean isDone() { return isDone; }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
