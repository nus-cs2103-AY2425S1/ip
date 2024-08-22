package main.java;

public class Event extends Task {

    protected String fromDate;
    protected String toDate;

    public Event (String name, String fromDate, String toDate) {
        super(name);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        String res = "[E]";
        res += super.toString();
        res += "(from: " + this.fromDate + " to: " + this.toDate + ")";
        return res;
    }
}
