public class Event extends Task{
    protected String from, to;

    public Event(String description, String fromTo) {
        super(description);
        String[] temp = fromTo.split("/to");
        this.from = temp[0];
        this.to = temp[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
