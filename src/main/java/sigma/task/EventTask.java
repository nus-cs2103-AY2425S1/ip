package sigma.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event task.
     * @return Start time of the event task.
     */
    public String getFrom() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        return from.format(formatter);
    }

    /**
     * Returns the end time of the event task.
     * @return End time of the event task.
     */
    public String getTo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        return to.format(formatter);
    }

    /**
     * Returns the type of the task.
     * @return String representation of type of the task.
     */
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the string representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                getStatusString(), getDesc(), getFrom(), getTo());
    }

}
