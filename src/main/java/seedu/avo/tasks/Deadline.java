package seedu.avo.tasks;

import seedu.avo.utils.DateTime;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate dueDate;
    public Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }
    @Override
    public boolean isOccurringOnDate(LocalDate date) { return date.equals(dueDate); }
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
