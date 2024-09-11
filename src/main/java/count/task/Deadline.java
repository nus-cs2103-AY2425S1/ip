package count.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class extends the Task class
 * changing the String representation to include the [D] symbol
 * includes a LocalDate to complete the task by
 */
public class Deadline extends Task {
    protected LocalDate endTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("LLLL dd yyyy");

    /**
     * Constructor for Deadline
     * @param description String description of the task
     * @param endTime String description of endTime in the format of inputFormat
     */
    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = LocalDate.parse(endTime, inputFormat);
    }

    /**
     * Constructor for Deadline
     * @param description String description of the task
     * @param endTime LocalDate description of endTime
     * @param completion Boolean whether the task is completed
     */
    public Deadline(String description, LocalDate endTime, boolean completion) {
        super(description, completion);
        this.endTime = endTime;
    }
    public LocalDate getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime.format(outputFormat) + ")";
    }
}
