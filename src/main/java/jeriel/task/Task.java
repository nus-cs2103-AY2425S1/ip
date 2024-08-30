package jeriel.task;
import jeriel.command.*;
import jeriel.util.*;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public abstract String toSaveFormat();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
