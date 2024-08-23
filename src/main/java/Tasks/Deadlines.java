package Tasks;

import Tasks.Task;

public class Deadlines extends Task {
    private String dueDate;
    public Deadlines(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    public String getExtraInfo() {
        return "(by: " + this.dueDate + ")";
    }
}
