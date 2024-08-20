public class EventTask extends Task {
    public EventTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
