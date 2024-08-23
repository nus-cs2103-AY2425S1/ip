public class Event extends Task {
    private final String startDateTime;
    private final String endDateTime;

    public Event(String description, String startDateTime, String endDateTime) throws EmptyArgumentException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
        if (startDateTime.isEmpty()) {
            throw new EmptyArgumentException("start date time");
        }
        if (endDateTime.isEmpty()) {
            throw new EmptyArgumentException("end date time");
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }
}
