package bob.task;

import bob.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String SYMBOL = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, false, from, to);
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public String getTaskLine() {
        return getSymbol() +  "," + isDoneBinary() + "," + description + ","
                + Parser.getDateTimeStr(from) + "," + Parser.getDateTimeStr(to);
    }

    public boolean isRelevant(LocalDate date) {
        LocalDate fromDate = from.toLocalDate();
        LocalDate toDate = to.toLocalDate();
        return !(date.isBefore(fromDate) || date.isAfter(toDate));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[" + getSymbol() + "]" + super.toString() + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }
}
