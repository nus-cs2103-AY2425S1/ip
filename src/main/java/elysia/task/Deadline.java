package elysia.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Extends the Task class to include deadline-specific details.
 **/
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by          The due date or time of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


    @Override
    public String saveToTxt() {
        int i = this.isDone ? 1 : 0;
        return "D | " + i + " | " + this.description + " | " + this.by;
    }
}
