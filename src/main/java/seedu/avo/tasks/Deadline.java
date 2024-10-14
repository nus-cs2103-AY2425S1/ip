package seedu.avo.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.avo.utils.DateTime;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {
    private final LocalDateTime dueDate;

    /**
     * @param name The name of the task
     * @param dueDate The deadline of the task
     */
    public Deadline(String name, LocalDateTime dueDate) {
        super(name);
        this.dueDate = dueDate;
    }
    @Override
    public boolean isOccurringOnDate(LocalDate date) {
        return date.equals(dueDate.toLocalDate());
    }
    @Override
    public String formatData() {
        String dueDateStr = DateTime.format(dueDate);
        return String.format("D : %s : %s", super.formatData(), dueDateStr);
    }
    @Override
    public String toString() {
        String dueDateStr = DateTime.format(dueDate);
        return String.format("[D] %s (by: %s)", super.toString(), dueDateStr);
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline d) {
            return super.equals(o) && dueDate.equals(d.dueDate);
        }
        return false;
    }
}
