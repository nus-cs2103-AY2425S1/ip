public class Event extends Task{
    String from;
    String to;

    public Event(String d, String f, String t) {
        super(d);
        this.from = f;
        this.to = t;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String additionalDescDetailsToFileFormat() {
        return " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from
                + " to: " + to + ")";
    }
}
