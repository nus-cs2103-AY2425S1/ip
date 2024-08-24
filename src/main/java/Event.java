public class Event extends Task {
    private String location;
    private String startTime;
    private String endTime;

    public Event(String name, String description, String startTime, String endTime, String location) {
        super(name, description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() +
                "\n\tStart Time: " + startTime +
                "\n\tEnd Time: " + endTime +
                "\n\tLocation: " + location;
    }
}
