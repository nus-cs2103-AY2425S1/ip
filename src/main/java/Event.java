public class Event extends Task {
    private String period;

    Event(String name, String details) {
        super(name);
        this.period = details;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (%s)", period);
    }
}
