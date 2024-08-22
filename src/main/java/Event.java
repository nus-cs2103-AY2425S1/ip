public class Event extends Task{
    private String fromDate;
    private String toDate;

    public Event(String name, String fromDate, String toDate) {
        super(name);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[E]%s (from %s to: %s)", temp, fromDate, toDate);
    }
}
