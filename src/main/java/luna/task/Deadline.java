package luna.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Creates a task with deadline.
     *
     * @param description Description of task.
     * @param deadline Deadline of task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a, E dd MMM yyyy");
        return "[D]" + super.toString() + " (Deadline: " + deadline.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        String status = super.isDone ? "1|" : "0|";
        return "D|" + status + super.description + "|" + deadline;
    }
}


