package neon;

public class Deadline extends Task {
    private String dateTime;
    private final String taskType = "D";

    public Deadline(String name, boolean isCompleted, String dateTime) {
        super(name, isCompleted);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]["+ this.checkMark() + "] " + this.getName()
                + " (by: " + this.dateTime + ")";
    }

    public String toTask() {
        return this.getTaskType() + "/"+ this.completeStatus() + "/" + this.getName()
                + "/" + processDate(this.dateTime);
    }

    public String getTaskType() {
        return this.taskType;
    }
}
