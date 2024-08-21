public class Event extends Task {
    private String startDay;
    private String endDay;
    public Event(String description, String startDay, String endDay) {
        super(description);
        this.startDay = startDay;
        this.endDay = endDay;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.startDay, this.endDay);
    }
}
