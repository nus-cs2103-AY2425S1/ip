public class Event extends Task{
    protected String from_msg;
    protected String to_msg;

    public Event(String description, String from_msg, String to_msg) {
        super(description);
        this.from_msg = from_msg;
        this.to_msg = to_msg;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from_msg + " to: " + this.to_msg +")";
    }
}
