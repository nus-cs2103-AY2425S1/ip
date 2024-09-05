package slave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * a Deadline object is a Task with a deadline
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a Deadline object with the specified parameters
     *
     * @param taskName is the task name
     * @param deadline is the deadline of the task
     */
    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Creates a Deadline object with the specified parameters
     *
     * @param isCompleted is the state of completeness fo the task
     * @param taskName    is the task name
     * @param deadline    is the deadline of the task
     */
    protected Deadline(boolean isCompleted, String taskName, LocalDate deadline) {
        super(isCompleted, taskName);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public LocalDate getRawDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }

    @Override
    public String save() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
