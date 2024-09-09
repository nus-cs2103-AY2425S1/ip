package monique.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import monique.exception.IllegalDateFormatException;


/**
 * The <code>Deadline</code> class represents a task with a specific deadline.
 * It extends the <code>Task</code> class and includes a due date for the task.
 */
public class Deadline extends Task {

    private static final String FORMAT_STRING = "[D][%s] %s (by: %s)";
    private final LocalDate by;

    /**
     * Constructs a new <code>Deadline</code> object with the specified description,
     * completion status, and due date.
     *
     * @param description The description of the task.
     * @param isComplete The completion status of the task.
     * @param by The due date of the task.
     */
    public Deadline(String description, boolean isComplete, LocalDate by) {
        super(description , isComplete);
        this.by = by;
    }

    /**
     * Constructs a new <code>Deadline</code> object with the specified description,
     * completion status, and due date in string format. The date is parsed from the
     * string using multiple acceptable formats.
     *
     * @param description The description of the task.
     * @param isComplete The completion status of the task.
     * @param by The due date of the task in string format.
     * @throws IllegalDateFormatException If the date string does not match any of the accepted formats.
     */
    public Deadline(String description, boolean isComplete, String by) throws IllegalDateFormatException {
        super(description , isComplete);

        LocalDate parsedDate = null;

        //accept the following date formats
        List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("M-d-yyyy HHmm"),
                DateTimeFormatter.ofPattern("M-d-yyyy")
        );
        for (DateTimeFormatter formatter: formatters) {
            try {
                parsedDate = LocalDate.parse(by, formatter);
                break;
            } catch (DateTimeParseException e) {
                //continue to next formatter
            }
        }

        if (parsedDate == null) {
            throw new IllegalDateFormatException();
        }
        this.by = parsedDate;
    }

    /**
     * Constructs a new <code>Deadline</code> object with default values: an empty description,
     * the task marked as complete, and an empty date string.
     *
     * @throws IllegalDateFormatException If the default date string is invalid (which it will be in this case).
     */
    public Deadline() throws IllegalDateFormatException {
        this("", true, "");
    }

    /**
     * Returns a string representation of the <code>Deadline</code> task.
     * The format is: "[D][status] description (by: date)" where status is "X" if the task is complete,
     * and the date is formatted as "MMM d yyyy".
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format(FORMAT_STRING, this.isComplete() ? "X" : " ",
                    this.getDescription() , this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Marks the <code>Deadline</code> task as complete and returns a new <code>Deadline</code> object
     * with the updated status.
     *
     * @return A new <code>Deadline</code> object with the status marked as complete.
     */
    @Override
    public Deadline mark() {
        return new Deadline(this.getDescription(), true, this.by);
    }

    /**
     * Unmarks the <code>Deadline</code> task as incomplete and returns a new <code>Deadline</code> object
     * with the updated status.
     *
     * @return A new <code>Deadline</code> object with the status marked as incomplete.
     */
    @Override
    public Deadline unmark() {
        return new Deadline(this.getDescription(), false, this.by);
    }


    /**
     * Returns the due date of the <code>Deadline</code> task.
     *
     * @return The due date of the task.
     */
    public LocalDate getBy() {
        return this.by;
    }


}
