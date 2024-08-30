package buddybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     *
     * @param description
     * @param start
     * @param end
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     *
     * @return
     */
    public String toFile() { //prototype in case of future modification
        return "E" + super.toFile() + "|" +
                start +  "|" + end;
    }

    /**
     *
     * @return
     */
    public String toString() { //prototype in case of future modification
        return "[E]" + super.toString() + " from: " +
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
