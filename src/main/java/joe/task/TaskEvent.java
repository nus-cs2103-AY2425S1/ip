package joe.task;

import static joe.Constants.TASK_EVENT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskEvent extends Task {
    private LocalDate from;
    private LocalDate to;

    public TaskEvent(String task, String from, String to) {
        super(task);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[%s]%s (from: %s to: %s)", TASK_EVENT, super.toString(), formattedFrom, formattedTo);
    }

    @Override
    public String toSaveString() {
        return String.format("%s|%d|%s|%s|%s", TASK_EVENT, isDone() ? 1 : 0, getTask(), from, to);
    }

    /**
     * @return The start date of the event.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * @return The end date of the event.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Checks if the dates are in the correct format and the start date is before
     * the end date.
     * 
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @return True if the dates are in the correct format, false otherwise.
     */
    public static boolean isValidFormat(String from, String to) {
        try {
            LocalDate.parse(from);
            LocalDate.parse(to);
            if (LocalDate.parse(from).isAfter(LocalDate.parse(to))) {
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
