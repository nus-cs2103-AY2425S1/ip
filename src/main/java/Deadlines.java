public class Deadlines extends Task {
    private String dueDate;
    protected Deadlines(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    protected String getExtraInfo() {
        return "(by: " + this.dueDate + ")";
    }
}
