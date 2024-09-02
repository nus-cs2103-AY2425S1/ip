package chatBot.task;

import chatBot.exception.EmptyDescException;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected static final DateTimeFormatter ORIGINALFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter PRINTFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    private final String description;
    private boolean isDone;

    Task(String description) throws EmptyDescException {
        if (description == "") {
            throw new EmptyDescException();
        }
        this.description = description;
        this.isDone = false;
    }

    private String getDescription() {
        return description;
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public Task markAsNotDone() {
        this.isDone = false;
        return this;
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public String getOriginalCommand() {
        return this.description;
    }

    public String toString() {
        String s = "[" + this.getStatusIcon() + "] ";
        s += this.getDescription();
        return s;
    }
}
