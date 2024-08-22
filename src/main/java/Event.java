public class Event extends Task{
    protected String taskType = "E";
    protected String startDT;
    protected String endDT;

    public Event(String description, String startDT, String endDT) {
        super(description);
        this.startDT = startDT;
        this.endDT = endDT;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.startDT + " to " + this.endDT + ")";
    }
}
