package lemon.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represent the deadline tasks that includes a date for the task to be done by
 * @author He Yiheng
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for the Deadline task
     * @param description description of the task
     * @param by date the task must be completed by
     * @param isDone whether the task is completed
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, "deadline", isDone);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return "D|" + isDone + "|" + description + "|" + by.toString() + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
