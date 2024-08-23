package matcha.task;
import java.time.format.DateTimeFormatter;
import matcha.exception.MatchaException;

public class Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static DateTimeFormatter getInputFormat() {
        return INPUT_FORMAT;
    }

    public static DateTimeFormatter getOutputFormat() {
        return OUTPUT_FORMAT;
    }


    public void markDone() throws MatchaException {
        if (this.isDone) {
            throw new MatchaException("Task is already marked as done!");
        }
        this.isDone = true;
    }

    public void markNotDone() throws MatchaException {
        if (!this.isDone) {
            throw new MatchaException("Task is already marked as not done!");
        }
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    public String toSaveString() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Checks if keyword is present in the task description.
     *
     * @param keyword The keyword to search for.
     * @return Returns true if keyword is found. Otherwise, returns false.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}