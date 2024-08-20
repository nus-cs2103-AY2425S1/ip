public class Event extends Task {

    protected String from;
    protected String to;


    public Event(String description, String from, String To) {
        super(description);
        this.from = from;
        this.to = To;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from+ "to: " + to +")";
    }
}
