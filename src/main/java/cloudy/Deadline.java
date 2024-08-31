package cloudy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the Cloudy program.
 * A Deadline is a task that has to be done by a specific date.
 */
public class Deadline extends Task{

    protected LocalDate deadline;

    /**
     * Initializes a Deadline task with the specified description and deadline.
     * @param description The description of the Deadline task.
     * @param deadline The deadline by which the task needs to be completed.
     * @param isMarked The completion status of the deadline task.
     */
    public Deadline(String description, LocalDate deadline, boolean isMarked) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    @Override
    public String printTaskOnList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = deadline.format(formatter);

        if (isMarked) {
            return "[D][X] " + this.description + " (by: " + formattedDate + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + formattedDate + ")";
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + (this.isMarked ? "1" : "0") + " | " + this.description + " | " + this.deadline;
    }
}
