package Dawn;
import java.time.LocalDate;

public class Event extends Task {
    String start;
    String end;
    LocalDate date;

    /**
     * Creates a new instance of an event with task description, start time, and end time of the event
     *
     * @param desc
     * @param start
     * @param end
     */
    public Event(String desc, String start, String end) {
        super(desc);
        int indexOfFrom = start.indexOf(" ") + 1;
        int indexOfEnd = end.indexOf(" ") + 1;
        this.start = start.substring(indexOfFrom).trim();
        this.date = LocalDate.parse(this.start.split(" ")[1]);
        this.end = end.substring(indexOfEnd).trim();
    }

    @Override
    public String toString() {
        return String.format("  [E][%s] %s (from: %s to: %s)",
                super.getStatusIcon(), super.toString(), this.start, this.end);
    }

    /**
     * Returns the start time of the event as a String
     */
    public String getFromTime() {
        return this.start;
    }

    /**
     * Returns the end time of the event as a String
     */
    public String getToTime() {
        return this.end;
    }

    /**
     * Returns the date of the event in LocalDate format
     */
    public LocalDate getDate() {
        return this.date;
    }
}
