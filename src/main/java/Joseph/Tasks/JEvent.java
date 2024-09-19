package Joseph.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JEvent class that extends from Task, with additional start and end timings.
 */
public class JEvent extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Initialises a new JEvent object.
     * @param desc The description of the event. Should be passed in as a String.
     * @param start The start timing of the event. Should be passed in as DD/M/YYYY HHmm.
     * @param end The end timing of event. Should be passed in as DD/M/YYYY HHmm.
     */
    public JEvent(String desc, String start, String end) {
        super(desc);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("dd/MM/yyyy HHmm"));
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String getDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.getDesc()
                + " start: " + this.start.format(formatter)
                + " end: " + this.end.format(formatter);
    }
}
