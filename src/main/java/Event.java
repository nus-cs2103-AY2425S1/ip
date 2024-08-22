public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event(String name, String start, String end) {
        super(name);
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + this.startDate
                + " to: "
                + this.endDate
                + ")";
    }
}
