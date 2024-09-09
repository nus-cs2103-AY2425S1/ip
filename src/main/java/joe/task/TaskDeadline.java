package joe.task;

import static joe.Constants.TASK_DEADLINE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskDeadline extends Task {
    private LocalDate by;

    public TaskDeadline(String task, String by) {
        super(task);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[%s]%s (by: %s)", TASK_DEADLINE, super.toString(), formattedBy);
    }

    @Override
    public String toSaveString() {
        return String.format("%s|%d|%s|%s", TASK_DEADLINE, isDone() ? 1 : 0, getTask(), by);
    }

    /**
     * @return The date of the deadline.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Postpones the deadline by a specified number of days.
     * 
     * @param days The number of days to postpone the deadline by.
     */
    public void postponeDeadline(int days) {
        by = by.plusDays(days);
    }

    /**
     * Checks if the date is in the correct format.
     * 
     * @param by The date of the deadline.
     * @return True if the date is in the correct format, false otherwise.
     */
    public static boolean isValidFormat(String by) {
        try {
            LocalDate.parse(by);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
