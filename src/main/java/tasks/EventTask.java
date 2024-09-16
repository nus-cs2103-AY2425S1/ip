package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private static final String SYMBOL = "E";
    private String from;
    private String to;
    private LocalDate fromDate;
    private LocalDate toDate;

    public EventTask(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.fromDate = LocalDate.parse(from);
        this.toDate = LocalDate.parse(to);
    }

    public EventTask(String description, String from, String to, String note) throws DateTimeParseException {
        super(description, note);
        this.fromDate = LocalDate.parse(from);
        this.toDate = LocalDate.parse(to);
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public String getTimings() {
        return "(from: " + this.fromDate + " to: " + this.toDate + ")";
    }

    @Override
    public String toString() {
        String taskString = String.format("[%s][%s] %s (from: %s to: %s)", this.SYMBOL, super.getStatusIcon(),
                        super.description, this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                                this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        taskString += "\nNote: " + super.note + "\n";
        return taskString;
    }
}
