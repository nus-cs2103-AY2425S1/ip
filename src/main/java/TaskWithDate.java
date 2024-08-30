import java.time.LocalDate;

public abstract class TaskWithDate extends Task {
    public TaskWithDate(String name, String symbol) {
        super(name, symbol);
    }

    public abstract String formatDate(LocalDate localDate);
    public abstract boolean overlapsWith(LocalDate localDate);
}
