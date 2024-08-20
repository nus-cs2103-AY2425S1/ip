public class Events extends Tasks {
    private String startDate;
    private String endDate;

    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    protected String typeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return typeIcon() + super.toString() + "(from: " + startDate + " to: " + endDate + ")";
    }

}
