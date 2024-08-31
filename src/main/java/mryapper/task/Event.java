package mryapper.task;

public class Event extends Task {
    private final String eventStart;
    private final String eventEnd;

    public Event(String description, String start, String end) {
        super(description);
        this.eventStart = start;
        this.eventEnd = end;
    }

    @Override
    public String getDataString() {
        return String.format("E ||| %d ||| %s ||| %s ||| %s\n",
                this.getStatus(), this.getDescription(), this.eventStart, this.eventEnd);
    }
    @Override
    public String toString() {
        String detailsString = String.format(" (from: %s | to: %s)", eventStart, eventEnd);
        return "[E]" + super.toString() + detailsString;
    }
}
