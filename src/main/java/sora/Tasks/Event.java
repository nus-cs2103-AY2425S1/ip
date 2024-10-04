package sora.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Event is a Task with starting time and ending time.
 */
public class Event extends Task {
    /** Starting time of the event in String Format */
    protected String from;
    /** Ending time of the event in String Format */
    protected String to;
    /** Starting time of the event in LocalDateTime Format */
    protected LocalDateTime fromDate;
    /** Ending time of the event in LocalDateTime Format */
    protected LocalDateTime toDate;

    /**
     * Constructs a new Event Task.
     * If from is a String & to is a String, this Constructor is called.
     *
     * @param description The description of the event.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event Task.
     * If fromDate is a LocalDateTime & toDate is a LocalDateTime, this Constructor is called.
     *
     * @param description The description of the event.
     * @param fromDate The starting time of the event.
     * @param toDate The ending time of the event.
     */
    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Constructs a new Event Task.
     * If fromDate is a LocalDateTime & to is a String, this Constructor is called.
     *
     * @param description The description of the event.
     * @param fromDate The starting time of the event.
     * @param to The ending time of the event.
     */
    public Event(String description, LocalDateTime fromDate, String to) {
        super(description);
        this.fromDate = fromDate;
        this.to = to;
    }

    /**
     * Constructs a new Event Task.
     * If from is a String & toDate is a LocalDateTime, this Constructor is called.
     *
     * @param description The description of the event.
     * @param from The starting time of the event.
     * @param toDate The ending time of the event.
     */
    public Event(String description, String from, LocalDateTime toDate) {
        super(description);
        this.from = from;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        String formattedFromDate = (this.fromDate != null)
                ? this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.from;
        String formattedToDate = (this.toDate != null)
                ? this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.to;
        return "[E]" + super.toString() + " (from: " + formattedFromDate + " to: " + formattedToDate + ")";
    }

    @Override
    public List<String> getTaskDetails() {
        String formattedFromDate = (this.fromDate != null)
                ? this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.from;
        String formattedToDate = (this.toDate != null)
                ? this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.to;
        return List.of("E", getStatus(), description, formattedFromDate, formattedToDate);
    }
}
