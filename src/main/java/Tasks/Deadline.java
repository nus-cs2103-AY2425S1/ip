package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime localDateTime;
    private final LocalDate localDate;

    /**
     * Constructor for a Deadline task.
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
    }

    /**
     * @return Returns the task icon, followed by its done/undone status.
     */
    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    /**
     * @return Returns the task description, followed by its deadline.
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
}
