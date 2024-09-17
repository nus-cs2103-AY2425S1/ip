package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class Deadline extends Task {
    private static final String format = "d/M/yyyy HHmm";
    private LocalDateTime by;
    private String savedBy;
    public Deadline(String description, String by) {
        super(description);
        this.savedBy = by;
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(format));
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.savedBy = by;
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(format));
    }
    /**
     * Returns a string representation of the Deadline task.
     *
     * @return a string representation of the Deadline task.
     */
    @Override
    public String toString() {
        assert description != null:"empty description";
        assert by != null:"empty due time";
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMMM dd yyyy, h:mm a", Locale.ENGLISH)) + ")";
    }
    /**
     * Returns a string representation of the Deadline task to be saved in a file.
     *
     * @return a string representation of the Deadline task in a format that will be saved in a file.
     */
    @Override
    public String save() {
        assert savedBy != null: "empty save time format";
        return "D | " + super.save() + " | " + savedBy;
    }
    /**
     * Returns the date and time associated with this task.
     *
     * @return the {@link LocalDateTime} object representing the task's deadline.
     */
    @Override
    public LocalDateTime getDateTime() {
        return by;
    }
}
