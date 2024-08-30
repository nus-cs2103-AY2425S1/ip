public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String name, String startDate, String endDate) {
        super(name);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString() + "(from: " + startDate + " to: " + endDate + ")";
    }
}
