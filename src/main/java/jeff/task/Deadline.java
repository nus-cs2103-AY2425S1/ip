package jeff.task;

import java.time.LocalDateTime;

import jeff.Storage;

/**
 * Represents a Deadline task with a description and a due date.
 *
 * A Deadline object stores information about a task that has to be completed by a specific date and time.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructs a Deadline object with the specified description and due date.
     *
     * @param task Description of the task.
     * @param dueDate Date and time by which the task should be completed.
     */
    public Deadline(String task, LocalDateTime dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Converts the {@code dueDate} field to a formatted string.
     * The date and time are formatted as "dd/MM/yyyy HHmm", which includes
     * the day, month, year, hour, and minute without a colon separating the hour and minute.
     *
     * @return a string representation of the {@code dueDate} field, formatted according to the specified pattern.
     */
    public String dateToString() {
        return dueDate.format(Storage.getDateTimeFormatter());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateToString() + ")";
    }

    @Override
    public String saveAsCsv() {
        return "D," + super.saveAsCsv() + "," + this.dateToString();
    }
}
