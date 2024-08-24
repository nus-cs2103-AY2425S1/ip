public class Event extends Task {
    private final String startDate;
    private final String endDate;

    Event(String description, String symbol, String startDate, String endDate) {
        super(description, symbol);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return task's startDate
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * @return task's endDate
     */
    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("(from: %s to: %s)", startDate, endDate);
    }
}
