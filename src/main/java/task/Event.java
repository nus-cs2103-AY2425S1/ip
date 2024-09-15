package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exception.DukeException;

/**
 * This class represents a normal Task type
 */
public class Event extends Task {
    private LocalDate formattedFrom;
    private LocalDate formattedTo;
    private String from;
    private String to;

    /**
     * Constructor of Event which is a type of task that includes a start
     * and end time for the task happening
     * @param description Description of task
     * @param from Start time of task
     * @param to End time of task
     * @throws DukeException An exception that happens due to invalid input
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        if (description.isEmpty() || description.equals("event")) {
            throw new DukeException("event", "OOPS!!! The description of a event shouldn't be empty!\n");
        }
        this.formattedFrom = LocalDate.parse(from);
        this.formattedTo = LocalDate.parse(to);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getString() {
        return "[E]" + super.getString() + " (from: "
                + formattedFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                    + formattedTo.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return this.description.equals(other.description) && this.from.equals(other.from)
                    && this.to.equals(other.to);
        }
        return false;
    }

    @Override
    public String getStorageFormat() {
        String output = this.isDone ? "1" : "0";
        output += " event " + description + " /from " + this.from + " /to " + this.to + "\n";
        return output;
    }

    public void setFrom(String from) {
        String trimmedFrom = from.trim();
        if (trimmedFrom.isEmpty()) {
            return;
        }
        this.from = from;
        this.formattedFrom = LocalDate.parse(from);
    }

    public void setTo(String To) {
        String trimmedTo = To.trim();
        if (trimmedTo.isEmpty()) {
            return;
        }
        this.from = To;
        this.formattedFrom = LocalDate.parse(To);
    }
}
