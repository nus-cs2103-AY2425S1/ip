import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        type = TaskType.DEADLINE;
    }

    public static Deadline fromTaskString(String taskString) {
        String[] parts = taskString.split("\\|");
        if (parts.length < 4)
            return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(parts[3], formatter);
        Deadline deadline = new Deadline(parts[2], dateTime);
        if (parts[1].equals("1"))
            deadline.markAsDone();

        return deadline;
    }

    protected String getTaskType() {
        return "D";
    }

    @Override
    public String toTaskString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return String.format("%s|%d|%s|%s",
                getTaskType(), isDone ? 1 : 0, description,
                formatter.format(deadline));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mm a");
        return String.format("[%s] %s (by: %s)", getTaskType(),
                super.toString(), formatter.format(deadline));
    }
}
