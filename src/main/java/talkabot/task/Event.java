package talkabot.task;

import talkabot.Parser;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Event class contains task info and start and end dates of task.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for Event class.
     */
    public Event(String[] details) {
        super(details[0]);
        this.from = Parser.stringToDate(details[1]);
        this.to = Parser.stringToDate(details[2]);
    }

    /**
     * Returns string representation of event.
     *
     * @return Event description.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.dateToString(this.from)
                + " to: " + Parser.dateToString(this.to) + ")";
    }

    /**
     * Returns string representation of event
     * to be stored in save file.
     *
     * @return Event description.
     */
    public String fileString() {
        return super.fileString() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns event dates as days of the week.
     *
     * @return Event days.
     */
    public String getDay() {
        return this.from.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault())
                + " to " + this.to.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}
