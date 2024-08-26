package Tasks;

import Utils.DateTime;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate dueDate;
    public Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }
    @Override
    public boolean isOccuringOnDate(LocalDate date) { return date.equals(dueDate); }
    @Override
    public String toString() {
        String dueDateStr = DateTime.format(dueDate);
        return String.format("[D] %s (by: %s)", super.toString(), dueDateStr);
    }
}
