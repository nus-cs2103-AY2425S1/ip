public class Events extends Task {
    private String startDate;
    private String endDate;

    protected Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    protected String getExtraInfo() {
        return "(from: " + this.startDate + "to: " + this.endDate + ")";
    }
}
