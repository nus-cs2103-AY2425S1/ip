package Tasks;

import Tasks.Task;

public class Events extends Task {
    private String startDate;
    private String endDate;

    public Events(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    public String getExtraInfo() {
        return "(from: " + this.startDate + "to: " + this.endDate + ")";
    }
}
