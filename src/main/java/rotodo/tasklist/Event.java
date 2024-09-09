package rotodo.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class encapsulates the specific type of
 * Task, called Events. Events are tasks that start
 * at a specific date/time and ends at a specific
 * date/time.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initialise the Event task.
     *
     * @param description of task
     * @param from datetime
     * @param to datetime
     * @param isDone status (can be true for loading data only)
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) + ")";
    }

    /**
     * Returns task in saveString format.
     */
    public String saveString() {
        return "E | " + super.saveString()
                + " | " + from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                + " | " + to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
