package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class that represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime localDateTime;
    private final LocalDate localDate;
    private final String key;

    /**
     * Constructor for a {@code Deadline} task.
     * @param description Description of the task.
     * @param deadline The deadline for the task, as a String.
     * @throws DateTimeException Thrown when the string for the deadline does not follow the appropriate format.
     */
    public Deadline(String description, String deadline) throws DateTimeException {
        super(description);

        localDateTime = DateHandler.parseLocalDateTime(deadline);
        localDate = DateHandler.parseLocalDate(deadline);

        if (localDateTime == null && localDate == null) {
            throw new DateTimeException("");
        }

        this.key = description + deadline;
    }

    /**
     *  Returns the task icon, followed by its done/undone status.
     * @return The task icon [D] and the task's done/undone status.
     */
    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    /**
     * Returns the task description, followed by its deadline.
     * @return The task description and its deadline.
     */
    @Override
    public String toString() {
        String date;

        if (localDateTime != null) {
            date = localDateTime.format(DateHandler.dateTimeFormat);
        } else {
            date = localDate.format(DateHandler.dateFormat);
        }

        return super.description + " (by: " + date + ") ";
    }

    /**
     * Returns the key of this {@code Deadline}.
     * <p>
     * The key is formed by concatenating the {@code description} and {@code deadline}
     * arguments passed into the {@code Deadline} constructor.
     * @return The key of this {@code Deadline}.
     */
    public String getKey() {
        return this.key;
    }
}
