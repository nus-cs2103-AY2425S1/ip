package max.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task that needs to be completed by a specific date and time.
 * It extends the Task class and includes additional attributes for the deadline time.
 */
public class Deadline extends Task {
    private String by;
    private LocalDateTime date;

    /**
     * Constructs a Deadline with a description and a deadline time as a string.
     *
     * @param description The description of the task.
     * @param by The deadline time as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline with a description and a deadline time as a LocalDateTime object.
     *
     * @param description The description of the task.
     * @param date The deadline time as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a string representation of the deadline, including its type, description,
     * and deadline time.
     *
     * @return A string in the format "[D][status] description (by: deadline)" where the deadline
     *         is formatted as "MMM dd yyyy HH:mm" if a LocalDateTime object is used, or as a string otherwise.
     */
    @Override
    public String toString() {
        //return String.format("[D]" + super.toString() + " (by: %s)", date == null ? by : date);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDate = (date != null) ? date.format(outputFormatter) : by;
        return String.format("[D]" + super.toString() + " (by: %s)", formattedDate);
    }


    /**
     * Returns the task in a format suitable for saving to a file.
     * The format includes the task type, completion status, description, due date,
     * and any tags associated with the task.
     *
     * @return A string representing the task formatted for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter converter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDate = (date != null) ? date.format(converter) : by;
        String tagsString = getTagsString();
        return String.format("D | %d | %s | %s%s", isDone ? 1 : 0, description, formattedDate,
                tagsString.isEmpty() ? "" : " | " + tagsString);
    }

}
