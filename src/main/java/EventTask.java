import java.time.LocalDateTime;

public class EventTask extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor to create a EventTask object
     *
     * @param description that describes what the task is
     * @param start of the event
     * @param end of the event
     */
    public EventTask(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public EventTask(
            String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * String representation of the task
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getDateString(this.start, "MMM dd yyyy hh:mm a")
                + " to: " + this.getDateString(this.end, "MMM dd yyyy hh:mm a")
                + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.getDateString(this.start, "yyyy-MM-dd HH:mm")
                + " | " + this.getDateString(this.end, "yyyy-MM-dd HH:mm");
    }
}
