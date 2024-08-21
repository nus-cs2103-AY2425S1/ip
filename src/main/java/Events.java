public class Events extends Task {
    private String startDate;
    private String endDate;
    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String toString() {
        String str = " (from: " + startDate + " to: " + endDate + ")";
        return "[E]" + super.toString() + str;
    }
}
