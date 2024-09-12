package Tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Task {
    //var that describes task
    protected String desc;

    //var that stores status of task
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    /**
     * marks item as complete
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks item as incomplete
     */
    public void unMark() {
        isDone = false;
    }

    /**
     * Returns string representation of task completion status
     * If done, return X, else return " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //getter
    public String getTaskDesc() {
        return desc; // mark done task with X
    }

    /**
     * Returns string representation of task
     */
    public String print() {
        return "[" + this.getStatusIcon() + "] " + desc;
    }

    /**
     * Sets the date of the task.
     *
     * @param updatedDate the new date to be assigned to the task.
     */
    public abstract void setDate(LocalDate updatedDate);

    /**
     * Sets the time of the task.
     *
     * @param updatedDeadlineTime the new time to be assigned to the task.
     */
    public abstract void setTime(LocalTime updatedDeadlineTime);

    /**
     * Sets the start time of the event.
     *
     * @param updatedEventStartTime the new start time to be assigned to the event.
     */
    public abstract void setStartTime(LocalTime updatedEventStartTime);

    /**
     * Sets the end time of the event.
     *
     * @param updatedEventEndTime the new end time to be assigned to the event.
     */
    public abstract void setEndTime(LocalTime updatedEventEndTime);
}
