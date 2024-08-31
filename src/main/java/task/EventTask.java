package task;
import prince.Prince;


import java.time.LocalDateTime;

/**
 * Represents a task for an event.
 *
 * A Eventtask is a type of task that includes a start time and end time
 * The class provides multiple methods to retrieve and save that information.
 */

public class EventTask extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a EventTask with the specified description and deadline in a particular format
     * @param description
     * @param start
     * @param end
     */
    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the task in a human-readable format.
     *
     * @return String
     */
    @Override
    public String printTask() {
        return "[E]" + super.printTask() +  " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns a string representation of the task in a file-storage format.
     *
     * @return String
     */
    @Override
    public String printFileFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + start + " | " + end;
    }
}
