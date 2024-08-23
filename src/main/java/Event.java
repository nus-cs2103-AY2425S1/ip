package main.java;

public class Event extends Task{
    private String from, to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %s to: %s)", this.from, this.to);
    }
}
