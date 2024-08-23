public class Event extends Task {
    private final String startDate;
    private final String endDate;

    public Event(String name, int count, String startDate, String endDate) {
        super(name, count);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String dateString = " (from: " + startDate + " to: " +
                endDate + ")";
        return "[E]" + super.toString() + dateString;
    }
}
