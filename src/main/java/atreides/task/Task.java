package atreides.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this("");
    }

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon(String trueString, String falseString) {
        return (isDone ? trueString : falseString);
    }


    public void markDone(Boolean var) {
        this.isDone = var;
    }

    public String write() {
        return " | " + this.getStatusIcon("1", "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon("X", " ") + "] " + this.description;
    }
}