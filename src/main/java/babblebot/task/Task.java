package babblebot.task;

public class Task {
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
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String toFileFormat() {
        if (getStatusIcon().equals("X")) {
            return "1" + " | " + this.description;
        } else {
            return "0" + " | " + this.description;
        }
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

