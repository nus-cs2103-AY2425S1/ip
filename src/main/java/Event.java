public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super("E", description);
        this.from = from;
        this.to  = to;
    }

    @Override
    public String stringUI() {
        return super.stringUI() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String stringStorage() {
        return super.stringStorage() + " | " + this.from + " | " + this.to;
    }

}
