import java.time.LocalDateTime;

class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public static String format = "event <description> /from <start date> /to <end date>";

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.start = from;
        this.end = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.toString() + " to " + end.toString() + ")";
    }
}
