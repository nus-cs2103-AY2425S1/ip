import java.time.LocalDate;

public class EventTask extends Task {
    private final String SYMBOL = "E";
    private String from;
    private String to;
    private LocalDate fromDate;
    private LocalDate toDate;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public EventTask(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return from != null ? String.format("[%s][%s] %s (from: %s to: %s)", this.SYMBOL, super.getStatusIcon(), super.description, this.from, this.to)
                : String.format("[%s][%s] %s (from: %s to: %s)", this.SYMBOL, super.getStatusIcon(), super.description, this.fromDate, this.toDate);
    }
}
