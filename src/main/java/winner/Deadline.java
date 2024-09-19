package winner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which includes a deadline which can be represented with a date or both a date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime byDateTime;

    /**
     * Creates a new Deadline task with the given description and date deadline.
     * The deadline is set to the start of the given date (i.e. midnight).
     *
     * @param description Description of Deadline task.
     * @param byDate Deadline date.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDateTime = byDate.atStartOfDay();
    }

    /**
     * Creates a new Deadline task with the given description and date and time deadline.
     *
     * @param description Description of Deadline task.
     * @param byDateTime Deadline date and time.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    /**
     * Returns a String representation of a Deadline task, including its completion status, description
     * and deadline.
     *
     * @return A String representing the deadline task, including its completion status, description and deadline.
     */
    @Override
    public String taskToString() {
        if (isDone) {
            return "[D] [X] " + description + " (by: " + formattedDeadline() + ")";
        }
        return "[D] [ ] " + description + " (by: " + formattedDeadline() + ")";
    }

    /**
     * Marks a Deadline task as done and returns a String confirming the task has been marked as done.
     *
     * @return A String indicating the task has been marked as done.
     */
    @Override
    public String markDone() {
        return super.markDone() + "\n"
                + " ".repeat(5) + "[D] [X] " + description + " (by: " + formattedDeadline() + ")";
    }

    /**
     * Marks a Deadline task as undone and returns a String confirming the task has been marked as undone.
     *
     * @return A String indicating the task has been marked as undone.
     */
    @Override
    public String unmarkDone() {
        return super.unmarkDone() + "\n"
                + " ".repeat(5) + "[D] [ ] " + description + " (by: " + formattedDeadline() + ")";
    }

    /**
     * Deletes a Deadline task and returns a String confirming the task has been deleted.
     *
     * @return A String indicating the task has been deleted.
     */
    @Override
    public String deleteTask() {
        if (isDone) {
            return super.deleteTask() + "\n"
                    + " ".repeat(5) + "[D] [X] " + description + " (by: " + formattedDeadline() + ")";
        }
        return super.deleteTask() + "\n"
                + " ".repeat(5) + "[D] [ ] " + description + " (by: " + formattedDeadline() + ")";
    }

    /**
     * Formats the deadline of a Deadline task as a String.
     *
     * @return A String representation of the deadline, including the day of the week, date and optionally, the time.
     */
    public String formattedDeadline() {
        if (byDateTime.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return byDateTime.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
        }
        return byDateTime.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy 'at' HHmm"));
    }

}
