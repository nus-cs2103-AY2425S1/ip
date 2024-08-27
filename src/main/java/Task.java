import java.time.format.DateTimeFormatter;

public class Task {

    private final String description;
    private boolean isDone;
    // For now, Deadline Tasks will take in date only, while Event Tasks will take in date and time
    protected static final DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected static final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected static final DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toFileFormat() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
