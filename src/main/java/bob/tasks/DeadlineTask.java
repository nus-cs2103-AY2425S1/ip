package bob.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a deadline task in the task list.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime by;

    /**
     * Creates a deadline task.
     *
     * @param description the description of the task
     * @param by the date and time the task is due
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public LocalDateTime getReminderDate() {
        return by;
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
     * Converts a String date and time to LocalDateTime
     *
     * @param dateTimeInput a String date and time
     * @return a LocalDateTime
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
