package Hoodini;

public class Event extends Input{

    private String from;
    private String to;

    public Event(String input) {
        super(input.split(" ", 2)[1].split("/", 2)[0]);
        this.from = input.split("/from", 2)[1].split("/to", 2)[0];
        this.to = input.split("/to", 2)[1];
    }

    public Event(String input, String from, String to) {
        super(input);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String str = "[E] " + super.toString() + "(from:" + this.from + " to:" + this.to + ")";
        return str;
    }
}
