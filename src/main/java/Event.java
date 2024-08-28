public class Event extends Task {

    private String from;
    private String to;
    
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        return "E," + done + "," + this.getName() + "," + this.from + "," + this.to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
