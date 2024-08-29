import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public abstract String toString();

    public abstract String toFileString();
}
