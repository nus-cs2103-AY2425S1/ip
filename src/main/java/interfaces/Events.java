package interfaces;

public class Events extends Task {
    protected String to;
    protected String from;

    /**
     * @param to   end of event.
     * @param from start of event.
     * @inheritDoc Stores start and end duration of event.
     */
    public Events(String description, String to, String from) {
        super(description.replace("event ", ""));
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to " + this.to + ")";
    }

    @Override
    public String loadString() {
        return "event " + this.description + "/from " + this.from + "/to " + this.to + " | " + this.isDone + " | " + this.tag;
    }

}
