package phenex.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class which encapsulates an Event task.
 */
public class Event extends TaskWithDate {
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs an Event object.
     *
     * @param name the name of the Event task.
     * @param startDate the start date of the Event task.
     * @param endDate the end date of the Event task.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name, "E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getEventStartDate() {
        return this.startDate;
    }

    public LocalDate getEventEndDate() {
        return this.endDate;
    }

    @Override
    public boolean overlapsWith(LocalDate localDate) {
        return (localDate.equals(this.startDate) || localDate.isAfter(this.startDate))
                && (localDate.equals(this.endDate) || localDate.isBefore(this.endDate));
    }

    @Override
    public String formatDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Event) {
            Event compared = (Event) object;
            return super.equals(compared)
                    && this.startDate.equals(compared.startDate)
                    && this.endDate.equals(compared.endDate);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.symbol + "]"
                + super.toString()
                + " (from: "
                + this.formatDate(this.startDate)
                + " to: "
                + this.formatDate(this.endDate)
                + ")";
    }
}
