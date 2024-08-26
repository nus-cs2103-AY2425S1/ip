import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskDeadline extends Task {
    private LocalDate by;

    public TaskDeadline(String task, String by) {
        super(task);
        this.by = LocalDate.parse(by);
  }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D]%s (by: %s)", super.toString(), formattedBy);
    }

    @Override
    public String toSaveString() {
        return String.format("D|%d|%s|%s", isDone() ? 1 : 0, getTask(), by);
    }
}
