package sigma.task;

/**
 * Represents a task.
 */
public class Task {
    private boolean status;
    private String desc;

    /**
     * Creates a task with the given description.
     * @param desc Description of the task.
     */
    public Task(String desc) {
        this.status = false;
        this.desc = desc;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * Returns the status of the task.
     * @return String representation of the status of the task.
     */
    public String getStatusString() {
        return status ? "X" : " ";
    }

    public String getTaskType() {
        return " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusString(), getDesc());
    }

}
