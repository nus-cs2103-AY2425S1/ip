package skibidi.task;

import java.io.IOException;
import java.time.LocalDate;

/** Abstract class representing a task. */
public abstract class AbstractTask {
    protected String description;
    protected boolean isDone;

    /**
     * Exception thrown when unable to deserialize a string into any subclasses
     * of AbstractTask.
     */
    public static class TaskDeserializationException extends Exception {
        public TaskDeserializationException(String message) {
            super(String.format("TASK PARSING ERROR: %s", message));
        }
    }

    protected AbstractTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected AbstractTask(String marker, String description) {
        this.description = description;
        this.isDone = marker.equals("X");
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

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Convert information about task to a string representation so that it can
     * be easily saved to disk.
     */
    public abstract String serialize() throws IOException;

    /**
     * Construct new instance of child class of AbstractTask, based on the raw
     * string provided.
     */
    public static AbstractTask deserialize(String rawString) throws IOException, TaskDeserializationException {
        String[] args = rawString.split("[|]");
        if (args.length == 0) {
            throw new TaskDeserializationException("Empty inputs given!");
        }
        switch (args[1]) {
        case "E":
            if (args.length != 5) {
                throw new TaskDeserializationException("Invalid format given for Event!");
            }
            return new Event(args[1], args[2], LocalDate.parse(args[3]), LocalDate.parse(args[4]));
        case "D":
            if (args.length != 4) {
                throw new TaskDeserializationException("Invalid format given for Deadline!");
            }
            return new Deadline(args[1], args[2], LocalDate.parse(args[3]));
        case "T":
            if (args.length != 3) {
                throw new TaskDeserializationException("Invalid format given for Todo!");
            }
            return new Todo(args[1], args[2]);
        default:
            throw new TaskDeserializationException("No task type matches the given indicator!");
        }
    }
}
