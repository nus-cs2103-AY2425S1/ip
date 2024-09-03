package neon;

public class Event extends Task {
    private String startDateTime;
    private String endDateTme;
    private final String taskType = "E";

    /**
     * Constructs a EventTask object with the specified description, start of event, and end of event.
     *
     * @param name - Description of task.
     * @param isCompleted - Whether the task is completed.
     * @param startDateTime - The deadline date and time of when the event starts.
     * @param endDateTime - The deadline date and time of when the event ends.
     */
    public Event(String name, boolean isCompleted, String startDateTime, String endDateTime) {
        super(name, isCompleted);
        this.startDateTime = startDateTime;
        this.endDateTme = endDateTime;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]["+ this.checkMark() + "] " + this.getName()
                + " (from: " + this.startDateTime + " to: " + this.endDateTme + ")";
    }

    /**
     * Returns the String representation of the task for storage.
     *
     * @return String representation for storage.
     */
    public String toTask() {
        return this.getTaskType() + "/"+ this.completeStatus() + "/" + this.getName()
                + "/" + processDate(this.startDateTime) + "/" + processDate(this.endDateTme);
    }

    /**
     * Returns the String character representing the task type.
     *
     * @return String representation of task type.
     */
    public String getTaskType() {
        return this.taskType;
    }
}
