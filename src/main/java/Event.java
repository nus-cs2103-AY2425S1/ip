package main.java;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event (String from, String to, String desc) {
        super('E',desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " (from: " + from + " to: " + to + ")";
    }
}
