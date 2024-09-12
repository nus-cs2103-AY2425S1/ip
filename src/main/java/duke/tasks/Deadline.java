package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /**
     * The deadline of the task.
     */
    private LocalDateTime deadline;

    /**
     * Constructor for a new deadline task.
     *
     * @param name the name of the deadline task
     *
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);

        assert deadline != null;
        this.deadline = deadline;
    }

    /**
     * Get the string representation of the deadline task.
     * @return the string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String deadlineString = this.deadline.format(f);
        return String.format("[D]%s (by: %s)", super.toString(), deadlineString);
    }
}
