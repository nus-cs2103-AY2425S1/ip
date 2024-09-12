package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private static final String SYMBOL = "E";
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

    public String getSymbol() {
        return SYMBOL;
    }

    public String getTimings() {
        return "(from: " + this.fromDate + " to: " + this.toDate + ")";
    }

    @Override
    public String toString() {
        String taskString =  from != null
                ? String.format("[%s][%s] %s (from: %s to: %s)", this.SYMBOL, super.getStatusIcon(),
                        super.description, this.from, this.to)
                : String.format("[%s][%s] %s (from: %s to: %s)", this.SYMBOL, super.getStatusIcon(),
                        super.description, this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                                this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        taskString += "\nNote: " + super.note + "\n";
        return taskString;
    }
}
