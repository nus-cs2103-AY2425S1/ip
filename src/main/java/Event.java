public class Event extends Task {
    private static final String SYMBOL = "E";
    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    public String getSymbol() {
        return SYMBOL;
    }

    public String getTaskLine() {
        return getSymbol() +  "," + isDoneBinary() + "," + description + "," + from + "," + to;
    }

    @Override
    public String toString() {
        return "[" + getSymbol() + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}
