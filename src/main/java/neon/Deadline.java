package neon;

public class Deadline extends Task {
    private String dateTime;
    private final String taskType = "D";

    /**
     * Constructs a DeadlineTask object with the specified description and deadline.
     *
     * @param name - Description of task.
     * @param isCompleted - Whether the task is completed.
     * @param dateTime - The deadline date and time.
     */
    public Deadline(String name, boolean isCompleted, String dateTime) {
        super(name, isCompleted);
        this.dateTime = dateTime;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]["+ this.checkMark() + "] " + this.getName()
                + " (by: " + this.dateTime + ")";
    }

    /**
     * Returns the String representation of the task for storage.
     *
     * @return String representation for storage.
     */
    public String toTask() {
        return this.getTaskType() + "/"+ this.completeStatus() + "/" + this.getName()
                + "/" + processDate(this.dateTime);
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
