package michael;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Initialises a new Deadline object.
     *
     * @param taskName Name of task to be done.
     * @param deadline Deadline by which task has to be done.
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = LocalDate.parse(deadline);
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        String formattedDeadline = this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
