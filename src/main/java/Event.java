public class Event extends Task {
    private final String startDate;
    private final String endDate;

    Event(String description, String symbol, String startDate, String endDate) {
        super(description, symbol);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
