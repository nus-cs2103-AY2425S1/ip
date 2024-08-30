public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    protected String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getTaskType(), super.toString());
    }
}
