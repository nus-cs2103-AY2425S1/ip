public class Event extends Task{

    protected String from;
    protected String to;

    public Event (String name, boolean done, String from, String to) {
        super(name, done);
        this.from = from;
        this.to = to;
    }

    public void setFrom(String newFrom) {
        this.from = newFrom;
    }

    public void setTo(String newTo) {
        this.to = newTo;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
