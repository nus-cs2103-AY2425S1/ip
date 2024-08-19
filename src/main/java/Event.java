public class Event extends Task {
    private final String startDate;
    private final String endDate;

    Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
