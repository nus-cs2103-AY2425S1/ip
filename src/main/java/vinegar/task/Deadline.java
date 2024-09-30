package vinegar.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Vinegar application.
 * <p>
 * A Deadline task contains a description and a deadline (date and time).
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and time in "yyyy-MM-dd HH:mm" format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string in the format "[D][ ] description (by: MMM d yyyy HH:mm)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }

    /**
     * Converts the deadline task to a file-friendly format.
     *
     * @return A string in the format "D | isDone | description | yyyy-MM-dd HH:mm".
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(inputFormatter);
    }
}
