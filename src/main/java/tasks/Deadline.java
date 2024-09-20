package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 * This class extends the Task class to include a deadline date.
 */
public class Deadline extends Task {
    private final LocalDate deadline;
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu");

    /**
     * Constructs a Deadline with the specified name and deadline date.
     *
     * @param task the name of the task
     * @param deadline the date by which the task must be completed
     */
    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task, including its name,
     * completion status, and the deadline date.
     * The format is "[ ] taskName | Deadline by deadlineDate".
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("%s | Deadline by %s", super.toString(), outputFormatter.format(deadline));
    }

    /**
     * Converts the deadline task into a string format suitable for saving to a file or database.
     * The format is "d,taskName,completionStatus,deadlineDate", where completionStatus is "y" if the task is done,
     * and "n" if it is not done.
     *
     * @return a string representation of the deadline task in the format "d,taskName,completionStatus,deadlineDate"
     */
    @Override
    public String toSaveFormat() {
        return String.format("d,%s,%s", super.toSaveFormat(), this.deadline);
    }
}
