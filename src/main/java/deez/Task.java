package deez;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public abstract class Task implements Serializable {
    protected static final DateTimeFormatter defaultDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy " +
            "hh:mma");
    protected boolean done;
    protected String description;

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public boolean toggleDone() {
        this.done = !this.done;
        return this.done;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return (this.done ? "[X] " : "[ ] ") + this.description;
    }

    public boolean isDone() {
        return this.done;
    }
}

