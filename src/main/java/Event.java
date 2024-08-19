public class Event extends Task {
    String startDate;
    String endDate;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.startDate + ", to: " + this.endDate + ")";
    }
}
