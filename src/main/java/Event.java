public class Event extends Task {
    private String from;
    private String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = "from: " + from;
        this.to = "to : " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.from + " " + this.to + ")";
    }
}
