package bibi.task;

import java.time.LocalDate;

/**
 * Represents the a Task with a description and a hard deadline.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructs a new Deadline tasks with the given deadline and description
     * @param description The description of the task.
     * @param deadline The deadline to complete the task by.
     */
    public Deadline(String description, String deadline) {
        // Call bibi.description.Task constructor
        super(description);

        // Parse the deadline appropriately
        if (deadline.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            LocalDate date = LocalDate.parse(deadline);
            this.deadline = String.format("%d %s %d",
                    date.getDayOfMonth(), date.getMonth().name(), date.getYear());
        } else {
            this.deadline = deadline;
        }

    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}
