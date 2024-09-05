package denim.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with an additional LocalDateTime deadline
 * This class provides methods to manage and retrieve the deadline's details.
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline with the specified description and deadline.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     * @param deadline The deadline of this task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }


    /**
     * Constructs a new Deadline with the specified description and deadline.
     *
     * @param description The description of the deadline.
     * @param isDone The completion status of the deadline.
     * @param deadline The deadline of this task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + deadline.format(dateFormatter) + ")";
    }

    @Override
    public String toSimplifiedString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String formattedString = String.format("D | %d | %s | %s\n", super.getIsDone() ? 1 : 0,
                super.getDescription(), deadline.format(dateFormatter));
        return formattedString;
    }
}
