package main.java;

public class Event extends Task{
    private String from, to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %s to: %s)", this.from, this.to);
    }

    @Override
    public String toFileString() {
        int markedInt = this.isMarked() ? 1 : 0;
        return String.format("E | %d | %s | %s | %s", markedInt, this.getName(), this.from, this.to);
    }
}
