package chatkaki.tasks;

import chatkaki.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;

    private DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Constructs a Deadline object with the specified description and by.
     *
     * @param description The description of the deadline.
     * @param by The by of the deadline.
     */
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Gets the by of the deadline.
     *
     * @return The by of the deadline.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Formats the deadline task to be saved in the file.
     * @return The formatted deadline task.
     */
    @Override
    public String fileFormat() {

        return "DEADLINE," + super.fileFormat() + "," + this.by.format(FORMATTER);
    }

    /**
     * Formats the deadline task to be displayed to the user.
     * @return The formatted deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }
}
