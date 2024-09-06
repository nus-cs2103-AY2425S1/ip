package rotodo.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates the specific type
 * of Task, called Deadlines. Events are tasks that
 * need to be done before a specific date/time.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Initialise the Deadline task.
     *
     * @param value description of task
     * @param by datetime
     * @param status done status (for loading data only)
     */
    public Deadline(String value, LocalDateTime by, boolean status) {
        super(value, status);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) + ")";
    }

    /**
     * Returns task in saveString format.
     */
    public String saveString() {
        return "D | " + super.saveString()
                + " | " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
