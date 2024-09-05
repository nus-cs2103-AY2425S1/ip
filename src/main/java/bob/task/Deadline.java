package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Task to be done by a specified deadline.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.marker = "/by";
    }

    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.marker = "/by";
    }

    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.marker = "/by";
    }

    // Returns the letter representing deadline.
    @Override
    public String taskLetter() {
        return "D";
    }

    /**
     * Returns a string representation of the file format in which we store the Deadline.
     */
    @Override
    public String getFileFormat() {
        String part1 = super.getFileFormat();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateTimeOutput = (date != null) ? date.format(outputFormatter) : by;
        return part1 + " | " + dateTimeOutput;
    }

    /**
     * Returns a string representation for a deadline task in the printed list.
     */
    @Override
    public String getTaskListItem() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateTimeOutput = (date != null) ? date.format(outputFormatter) : by;
        return super.getTaskListItem() + " (by: " + dateTimeOutput + ")";
    }
}
