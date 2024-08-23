public class Event extends Task {
    private final String startDate;
    private final String endDate;

    public Event(String task, String startDate, String endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("%s | Event from %s to %s", super.toString(), this.startDate, this.endDate);
    }
}
