package bob.task;

import bob.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String SYMBOL = "D";
    protected LocalDateTime by;

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by) {
        this(description, false, by);
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public String getTaskLine() {
        return getSymbol() +  "," + isDoneBinary() + "," + description + "," + Parser.getDateTimeStr(by);
    }

    public boolean isRelevant(LocalDate date) {
        LocalDate byDate = by.toLocalDate();
        return byDate.equals(date);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[" + getSymbol() + "]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
