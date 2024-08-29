package yapper.resources;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDesc() {
        return "| E | " + super.getDesc() + " | " + formattedDate(this.from) + " ----- " + formattedDate(this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formattedDate(this.from) + " to: " + formattedDate(this.to) + ")";
    }
}
