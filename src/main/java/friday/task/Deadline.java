package friday.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task. A Deadline is a task with a due date and time.
 */
public class Deadline extends Task {
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HHmm";
    private static final String DISPLAY_FORMAT_PATTERN = "MMM dd yyyy, h:mm a";
    private LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description, due date and time, and completion status.
     *
     * @param description The description of the Deadline.
     * @param by          The due date and time in "yyyy-MM-dd HHmm" format.
     * @param isDone      True if the task is done, false otherwise.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        assert by != null && !by.isEmpty() : "Due date should not be null or empty";
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
    }

    /**
     * Constructs a Deadline with the specified description and due date/time.
     * The task is not done by default.
     *
     * @param description The description of the Deadline.
     * @param by          The due date and time in "yyyy-MM-dd HHmm" format.
     */
    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public LocalDate getBy() {
        return by.toLocalDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern(DISPLAY_FORMAT_PATTERN)) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (super.isTaskDone() ? "1" : "0") + " | " + super.getDescription() + " | "
                + by.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
    }
}