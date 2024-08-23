public class Deadline extends Task {
    private String dateTime;
    private final String taskType = "D";

    public Deadline(String name, boolean completed, String dateTime) {
        super(name, completed);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]["+ this.checkMark() + "] " + this.getName()
                + " (by: " + this.dateTime + ")";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
