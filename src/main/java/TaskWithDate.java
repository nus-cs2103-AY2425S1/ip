import java.time.LocalDate;

public abstract class TaskWithDate extends Task {
    public TaskWithDate(String name) {
        super(name);
    }

    public abstract String formatDate(LocalDate localDate);
    public abstract boolean overlapsWith(LocalDate localDate);
}
