package blob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that has a deadline set.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String name, boolean isDone, String deadline) {
        super(name,isDone);
        this.deadline = LocalDateTime.parse(deadline);
        assert this.deadline.isAfter(LocalDateTime.now()) : "Deadline of task is already in the past!";
        super.type = "D";
    }

    /**
     * @return String representation of the deadline task in the form "[D] ['completion status] 'task name' (by: 'MMM d HH:mm')"
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.check() + "] " + this.name + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d HH:mm")) + ")";
    }

    /**
     * @return LocalDateTime of the tasks deadline
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
