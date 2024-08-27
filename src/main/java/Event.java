import java.time.LocalDate;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;
    public Event(String body, LocalDate start, LocalDate end) {
        super(body);
        this.start = start;
        this.end = end;
    }

    public Event(String body, boolean isDone, LocalDate start, LocalDate end) {
        super(body, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), this.start, this.end);
    }

    @Override
    public String saveString() {
        return "event," + super.saveString() + "," + this.start + "," + this.end;
    }
}
