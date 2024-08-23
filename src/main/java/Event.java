public class Event extends Task {
    protected String fromTo;

    public Event(String description, String fromTo) {
        super(description);
        this.fromTo = fromTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.fromTo + ")";
    }
}
