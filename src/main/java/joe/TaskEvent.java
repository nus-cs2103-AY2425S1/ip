package joe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        return String.format("[E]%s (from: %s to: %s)", super.toString(), formattedFrom, formattedTo);
    }

    @Override
    public String toSaveString() {
        return String.format("E|%d|%s|%s|%s", isDone() ? 1 : 0, getTask(), from, to);
    }
}
