package seedu.avo.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.avo.utils.DateTime;

public class Deadline extends Task {
    private final LocalDateTime dueDate;
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
}
