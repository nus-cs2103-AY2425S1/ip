public class Event extends Task {
    private String from;
    private String to;

    Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    Event(boolean isDone, String name, String from, String to) {
        super(isDone, name);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(), this.from, this.to);
    }

    public String convertToTxt() {
        return String.format("%s,%s,%s,%s","E", super.convertToTxt(), this.from, this.to);
    }
}
