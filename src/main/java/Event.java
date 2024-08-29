public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String saveAsCSV() {
        return "E," + super.saveAsCSV() + "," + this.from + "," + this.to;
    }
}
