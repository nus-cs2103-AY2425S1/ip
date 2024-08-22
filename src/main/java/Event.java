public class Event extends Task {
    protected String endDate, startDate;
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.endDate = endDate;
        this.startDate = startDate;
    }
    @Override
    public String listedString(){
        return super.toString() + " [from: " + this.startDate + " | to: " + this.endDate + "]";
    }
}
