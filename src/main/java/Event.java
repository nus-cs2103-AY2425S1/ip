public class Event extends Task{
    protected String startDate;
    protected String endDate;
    public Event(String description, String start, String end) {
        super(description);
        this.startDate = start;
        this.endDate = end;
    }
    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
