public class Events extends Task {
    private String startOfEvent;
    private String endOfEvent;

    public Events(String description, String startOfEvent, String endOfEvent) {
        super(description);
        this.startOfEvent = startOfEvent;
        this.endOfEvent = endOfEvent;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + startOfEvent + "to: " + endOfEvent + ")";
    }
}