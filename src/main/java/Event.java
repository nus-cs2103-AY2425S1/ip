public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String taskName, String timeframe) {
        super(taskName);
        this.start= timeframe.substring(timeframe.indexOf("/from") + 6, timeframe.indexOf("/to"));
        this.end = timeframe.substring(timeframe.indexOf("/to") + 4);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + start + " to: "+ end + ")";
    }
}
