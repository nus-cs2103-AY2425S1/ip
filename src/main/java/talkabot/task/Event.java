package talkabot.task;

import talkabot.Parser;

import java.time.format.TextStyle;
import java.time.LocalDate;
import java.util.Locale;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String[] details) {
        super(details[0]);
        this.from = Parser.stringToDate(details[1]);
        this.to = Parser.stringToDate(details[2]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.dateToString(this.from)
                + " to: " + Parser.dateToString(this.to) + ")";
    }

    public String fileString() {
        return super.fileString() + " | " + this.from + " | " + this.to;
    }

    public String getDay() {
        return this.from.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault())
                + " to " + this.to.getDayOfWeek().getDisplayName(TextStyle.FULL,
                Locale.getDefault());
    }
}
