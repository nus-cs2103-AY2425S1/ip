package bob.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new deadline task.
     * Each deadline task has a due date.
     *
     * @param description The description of the task.
     * @param by The deadline date and time.
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getBy() {
        return by.toString();
    }
    @Override
    public String getType() {
        return "D";
    }

    @Override
    public LocalDate getDate() {
        return by.toLocalDate();
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeInput The date and time string.
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateTimeInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTimeInput, formatter);
    }

    @Override
    public String toString() {
        String byFormatted = by.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma"));
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}
