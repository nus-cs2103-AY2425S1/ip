import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable{
    private static final long serialVersionUID = 3L;
    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("dd-MM-yyyy kkmm");
    protected static final DateTimeFormatter prettyDateTimeFormatter = 
            DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
    protected final boolean isDone;
    protected final String taskDescription;

    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }

    protected Task(boolean isDone, String taskDescription) {
        this.isDone = isDone;
        this.taskDescription = taskDescription;
    }

    public Task markAsDone() {
        return isDone
            ? this
            : new Task(true, this.taskDescription);
    }

    public Task markAsUndone() {
        return isDone
            ? new Task(this.taskDescription)
            : this;
    }

    @Override
    public String toString() {
        return isDone
            ? "[X] " + taskDescription
            : "[ ] " + taskDescription;
    }
}
