package darkpool.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected final String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
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
