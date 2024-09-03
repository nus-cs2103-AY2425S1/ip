package neon;

public class Event extends Task {
    private String startDateTime;
    private String endDateTme;
    private final String taskType = "E";
    public Event(String name, boolean isCompleted, String startDateTime, String endDateTime) {
        super(name, isCompleted);
        this.startDateTime = startDateTime;
        this.endDateTme = endDateTime;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]["+ this.checkMark() + "] " + this.getName()
                + " (from: " + this.startDateTime + " to: " + this.endDateTme + ")";
    }

    public String toTask() {
        return this.getTaskType() + "/"+ this.completeStatus() + "/" + this.getName()
                + "/" + processDate(this.startDateTime) + "/" + processDate(this.endDateTme);
    }


    public String getTaskType() {
        return this.taskType;
    }
}
