public class Event extends Task{
    public Event(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[E]%s", super.toString());
    }
}
