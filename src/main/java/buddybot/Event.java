package buddybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Event
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructor for Event
     * @param description
     * @param start
     * @param end
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description, TaskType.EVENT);
        assert start != null : "Start time should not be empty!";
        assert end != null : "End time should not be empty!";
        this.start = start;
        this.end = end;
    }

    /**
     * Return a String of the Event the file reader recognises
     * @return
     */
    public String toFile() { //prototype in case of future modification
        return "E" + super.toFile() + "|" +
                start +  "|" + end;
    }

    /**
     * Return a String of the Event with its status and description and date
     * @return
     */
    public String toString() { //prototype in case of future modification
        return "[E]" + super.toString() + " from: " +
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
