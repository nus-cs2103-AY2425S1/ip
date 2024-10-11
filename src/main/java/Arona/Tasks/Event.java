package arona.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for the events class which encapsulates a task with a set start and end date
     * @param  description  the name of the task
     * @param  from  the start date given in LocalDate readable format, time not included
     * @param  to  the start date given in LocalDate readable format, time not included
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getCategory() {
        return "[E]";
    }

    /**
     * A variant of toString that returns a nicer human friendly date format
     * @return String that looks like: [X][E] Event from 1 Aug 2024 to 11 Aug 2024
     */
    public String toFriendlyString() {
        return getStatusIcon() + getCategory() + " " + getDescription()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    @Override
    public String toString() {
        return getStatusIcon() + getCategory() + " " + getDescription() + " (from: " + from + " to: " + to + ")";
    }
}
