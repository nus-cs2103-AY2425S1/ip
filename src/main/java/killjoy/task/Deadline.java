package killjoy.task;

import java.time.LocalDateTime;

/**
 * Represents a task of type Deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the task.
     * @param by          The by date of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getTaskInfo() {
        return "DEADLINE|"
                + String.valueOf(isDone ? 1 : 0)
                + "|" + this.description + "|"
                + String.valueOf(this.by).replace("T", " ")
                + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
                + by.format(DATE_TIME_OUTPUT_FORMATTER) + ")";
    }

}
