package thebotfather.task;

import thebotfather.util.TheBotFatherException;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Task {

    protected static final DateTimeFormatter DATE_STRING_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected static final DateTimeFormatter DATE_STRING_FORMATTER_PRINT =
            DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    protected boolean done = false;
    protected String description;
    private String type = "";

    public Task(String description, String type) throws TheBotFatherException {
        if (Objects.equals(description, ""))
            throw new TheBotFatherException("Kid, Learn to read, where is the description??");
        this.description = description;
        this.type = type;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public boolean isWordInDescription(String word) {
        return description.contains(word);
    }

    protected String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFile() {
        return this.type + " | " + (this.isDone() ? "1" : "0") + " | " + this.description;
    }

}