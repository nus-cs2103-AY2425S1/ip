public class Event extends Task {
    private String type, startDateTime, endDateTime;

    public Event(String desc, String type, String startDateTime, String endDateTime, boolean isDone) {
        super(desc, isDone);
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + "from: " + this.startDateTime + " to: " + this.endDateTime + ")";
    }
}
