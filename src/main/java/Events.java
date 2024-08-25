package main.java;

public class Events extends Task {
    protected String from;
    protected String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getCategory() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
