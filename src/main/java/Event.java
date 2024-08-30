public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        if (getStatusIcon().equals("X")) {
            return "E" + " | " + "1" + " | " + this.description + " | " + this.from + " to " + this.to;
        } else {
            return "E" + " | " + "0" + " | " + this.description + " | " + this.from + " to " + this.to;
        }
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
