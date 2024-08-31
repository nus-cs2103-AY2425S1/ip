package bob;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public static Task from(String text) {
        if (text.startsWith("T")) {
            return ToDo.from(text);
        }
        if (text.startsWith("E")) {
            return Event.from(text);
        }
        if (text.startsWith("D")) {
            return Deadline.from(text);
        }
        return null;
    }

    public abstract String toText();

    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}