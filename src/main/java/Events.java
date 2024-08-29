public class Events extends Task {
    private String startDate;
    private String endDate;
    public Events(String description, String startDate, String endDate) {
        super(description, TaskType.EVENT);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toOutput() {
        int checkMark = this.isDone ? 1 : 0;
        return "E | " + checkMark + " | " + this.description + " | " +
                this.startDate + "-" + this.endDate;
    }
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
