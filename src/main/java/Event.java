import java.io.IOException;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    protected void save() throws IOException {
        Storage.getWriter().write("E | " + (super.getIsDone() ? "1" : "0") + " | " + super.getDescription() + " | " + from + " | " + to + "\n");
    }
}
