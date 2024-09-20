package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Task to be done by a specified deadline.
 */
public class Deadline extends Task {

    private static final String TASK_LETTER = "D";
    protected String endDate;
    protected LocalDate date;
    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Constructor.
     */
    public Deadline() {
        super();
        this.endDate = "";
    }

    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, String endDate, boolean isDone) {
        super(description, isDone);
        this.endDate = endDate;
    }

    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, String endDate, boolean isDone, String tag) {
        super(description, isDone, tag);
        this.endDate = endDate;
    }

    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    // Returns the letter representing deadline.
    @Override
    public String getTaskLetter() {
        return TASK_LETTER;
    }

    /**
     * Returns a string representation of the file format in which we store the Deadline.
     */
    @Override
    public String getFileFormat() {
        String deadlineFileFormat = super.getFileFormat();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateTimeOutput = (date != null) ? date.format(outputFormatter) : endDate;
        deadlineFileFormat += " | " + dateTimeOutput;
        if (!getTag().equals("")) {
            deadlineFileFormat += " | " + getTag();
        }
        return deadlineFileFormat;
    }

    /**
     * Returns a string representation for a deadline task in the printed list.
     */
    @Override
    public String getTaskListItem() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateTimeOutput = (date != null) ? date.format(outputFormatter) : endDate;
        return super.getTaskListItem() + " (by: " + dateTimeOutput + ")";
    }

    /**
     * Tags the task.
     *
     * @param tag Tag.
     */
    @Override
    public void tagTask(String tag) {
        this.setTag(tag);
    }

    /**
     * Tags the task.
     */
    @Override
    public void untagTask() {
        this.setTag("");
    }

}
