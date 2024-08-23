public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String start, String end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
        this.symbol = "[E]";
    }

    public String toString() { //prototype in case of future modification
        return super.toString() + " from: " + this.startTime + " |to: " + this.endTime;
    }
}
