package stobberi.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * The task has a description, a deadline date and time, and a completion status.
 * The deadline is stored as a LocalDateTime object.
 */
public class Deadline extends Task {
    private LocalDateTime deadlineOfTask;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     * The deadline is parsed from a string in the format "dd-MM-yyyy HHmm'hrs'".
     *
     * @param description The description of the task.
     * @param deadlineOfTask The deadline of the task in the format "dd-MM-yyyy HHmm'hrs'".
     */
    public Deadline(String description, String deadlineOfTask) {
        super(description);
        this.deadlineOfTask = LocalDateTime.parse(deadlineOfTask, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
    }

    /**
     * Constructs a Deadline object with the specified description, deadline, and completion status.
     * The deadline is parsed from a string in the format "dd-MM-yyyy HHmm'hrs'".
     * If the task is marked as done, the status is updated accordingly.
     *
     * @param description The description of the task.
     * @param deadlineOfTask The deadline of the task in the format "dd-MM-yyyy HHmm'hrs'".
     * @param done The completion status of the task. If "1", the task is marked as done.
     */
    public Deadline(String description, String deadlineOfTask, String done) {
        super(description);
        this.deadlineOfTask = LocalDateTime.parse(deadlineOfTask, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        if (done.equals("1")) {
            super.setDone();
        }
    }

    /**
     * Checks if the deadline of the task falls within a specific date.
     * The date is provided as a string in the format "dd-MM-yyyy".
     *
     * @param date The date to check, in the format "dd-MM-yyyy".
     * @return true if the deadline is during the specified date, false otherwise.
     */
    public boolean isDuring(String date) {
        LocalDateTime start = LocalDateTime.parse(date + " 0000hrs",
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        LocalDateTime end = LocalDateTime.parse(date + " 2359hrs", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));

        return (deadlineOfTask.isAfter(start) || deadlineOfTask.isEqual(start))
                && (deadlineOfTask.isBefore(end) || deadlineOfTask.isEqual(end));
    }

    /**
     * Returns the deadline of the task as a formatted string.
     * The format used is "dd-MM-yyyy HHmm'hrs'".
     *
     * @return The deadline of the task as a string.
     */
    public String getDeadlineOfTask() {
        return deadlineOfTask.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
    }

    /**
     * Returns a string representation of the Deadline object.
     * The format includes the task type, description, and deadline.
     *
     * @return A string representing the Deadline object.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
                + deadlineOfTask.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma")) + ")";
    }
}
