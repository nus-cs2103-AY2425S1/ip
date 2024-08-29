public class Deadlines extends Task {
    private String endDate;
    public Deadlines(String description, String endDate) {
        super(description, TaskType.DEADLINE);
        this.endDate = endDate;
    }

    public String toOutput() {
        int checkMark = this.isDone ? 1 : 0;
        return "D | " + checkMark + " | " + this.description + " | " + this.endDate;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + this.endDate + ")";
    }
}
