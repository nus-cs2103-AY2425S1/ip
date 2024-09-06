package yap.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Constructs a task that has a deadline, with the task's description and deadline.
     *
     * @param description a description of what the task is.
     * @param deadline the deadline for the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Constructs a task that has a deadline, with the task's description and deadline.
     * The task can either be done or not done.
     *
     * @param description a description of what the task is.
     * @param deadline the deadline for the task.
     * @param isDone whether the task has been completed or not.
     */
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Converts the details of the file into the format represented in the storage file.
     *
     * @return A string in the appropriate format, E | Completion Status (0 or 1) | Description | Deadline
     */
    @Override
    public String convertToFileString() {
        return "D | " + super.convertToFileString() + String.format(" | %s\n", deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
