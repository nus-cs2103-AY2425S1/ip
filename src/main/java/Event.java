public class Event extends Task{
    private String startDate;
    private String endDate;

    Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" +
               super.toString() +
               "(" + startDate + "-" + endDate +")";
    }
}
