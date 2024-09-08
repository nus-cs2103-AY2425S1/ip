package chacha.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Task that is a Deadline for the user.
 *
 */
public class DeadlineTask extends Task {
    protected LocalDate date;

    /**
     * Creates a DeadlineTask, initialising the description, isDone status and date.
     * @param description
     * @param isDone
     * @param date
     */
    public DeadlineTask(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns a string representation to be printed.
     *
     * @return String representation.
     */
    @Override
    public String printTask() {
        String output = "[D]";
        String status = (super.isDone ? "X" : " ");
        return output + "[" + status + "] " + super.description
                + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation to be written in chacha.txt.
     *
     * @return String representation.
     */
    @Override
    public String writeTask() {
        String output = "D | ";
        String status = (super.isDone ? "1" : "0");
        output += status + " | ";
        return output + description + " | " + date.toString() + "\n";
    }
}
