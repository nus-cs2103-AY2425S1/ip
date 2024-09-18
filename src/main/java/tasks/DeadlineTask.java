package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline. A DeadlineTask has a description, a deadline date,
 * and an optional note.
 */
public class DeadlineTask extends Task {
    private static final String SYMBOL = "D";
    private LocalDate deadlineDate;

    /**
     * Constructs a DeadlineTask with a description and a due date.
     *
     * @param description The description of the deadline task.
     * @param dueTime The due date in the format YYYY-MM-DD.
     * @throws DateTimeParseException If the due time is not in the correct format.
     */
    public DeadlineTask(String description, String dueTime) throws DateTimeParseException {
        super(description);
        this.deadlineDate = LocalDate.parse(dueTime);
    }

    /**
     * Constructs a DeadlineTask with a description, a due date, and a note.
     *
     * @param description The description of the deadline task.
     * @param dueTime The due date in the format YYYY-MM-DD.
     * @param note An optional note associated with the deadline task.
     * @throws DateTimeParseException If the due time is not in the correct format.
     */
    public DeadlineTask(String description, String dueTime, String note) throws DateTimeParseException {
        super(description, note);
        this.deadlineDate = LocalDate.parse(dueTime);
    }

    /**
     * Gets the symbol representing the type of task.
     *
     * @return A string symbol representing this task type ("D" for DeadlineTask).
     */
    public String getSymbol() {
        return SYMBOL;
    }

    /**
     * Gets a string representation of the timings associated with the task.
     *
     * @return A string indicating the deadline date of the task.
     */
    public String getTimings() {
        return "(by: " + this.deadlineDate + ")";
    }

    /**
     * Returns a string representation of the DeadlineTask, including its status,
     * description, deadline date, and any associated note.
     *
     * @return A formatted string representing the DeadlineTask.
     */
    @Override
    public String toString() {
        String taskString = String.format("[%s][%s] %s (by: %s)", this.SYMBOL, super.getStatusIcon(),
                super.description, this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        taskString += "\nNote: " + super.note + "\n";
        return taskString;
    }
}
