public class Event extends Task {
    private String startDT;
    private String endDT;
    private final String taskType = "E";
    public Event(String name, boolean completed, String startDT, String endDT) {
        super(name, completed);
        this.startDT = startDT;
        this.endDT = endDT;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]["+ this.checkMark() + "] " + this.getName()
                + " (from: " + this.startDT + " to: " + this.endDT + ")";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
