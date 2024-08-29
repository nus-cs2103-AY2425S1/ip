package neuro.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Deadline} class represents a deadline task, which is a type of task with a description
 * and deadline time/date.
 * It extends the {@link Task} class, inheriting its properties and methods.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy[ HHmm]")) + ")";
    }

    @Override
    public String toSaveData() {
        return "D | " + super.toSaveData() + " | " + this.deadline;
    }
}
