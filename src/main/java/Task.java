import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public abstract String toFileFormat();

    public static Task fromFileFormat(String data) throws ArtsException {
        String[] parts = data.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                LocalDateTime by = LocalDateTime.parse(parts[3], formatter);
                task = new Deadline(description, by);
                break;
            case "E":
                LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
                LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
                task = new Event(description, from, to);
                break;
            default:
                throw new ArtsException("Unknown task type.");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
