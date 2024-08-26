public class Event extends Task{
    private final String startDate;
    private final String endDate;

    public Event(String title, String startDate, String endDate) {
        super(title);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String getStartDateString() {
        return String.format(" (from: %s to: %s)", startDate, endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getStartDateString();
    }
}
