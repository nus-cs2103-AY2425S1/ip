package meerkatpack;

public class Event extends Task {
    private String start;
    private String end;
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toParseableString() {
        String s = "e,";
        if (this.isCompleted()) {
            s += "m,";
        }
        else {
            s += "u,";
        }
        s += this.getName() + "," + this.start + "," + this.end;
        return s;
    }
}
