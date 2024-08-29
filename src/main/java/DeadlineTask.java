import java.time.LocalDate;

public class DeadlineTask extends Task {
    private final String SYMBOL = "D";
    private String dueTime;
    private LocalDate date;

    public DeadlineTask(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    public DeadlineTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return dueTime != null ? String.format("[%s][%s] %s (by: %s)", this.SYMBOL, super.getStatusIcon(), super.description, this.dueTime)
                : String.format("[%s][%s] %s (by: %s)", this.SYMBOL, super.getStatusIcon(), super.description, this.date);
    }
}
