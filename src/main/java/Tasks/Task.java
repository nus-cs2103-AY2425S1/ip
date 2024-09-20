package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
     * Compares this Task object to the specified object for equality.
     * <p>
     * Two Task objects are considered equal if they are of the same class and their
     * descriptions are equal. This method first checks if the two references are
     * identical, then verifies that the object being compared is of the same class
     * and is not null. If these conditions are met, it compares the description fields
     * of both Task objects to determine equality.
     * </p>
     *
     * @param o the object to be compared for equality with this Task
     * @return true if this Task is equal to the specified object;
     *         false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return desc.equals(task.desc);
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
     * Sets the date and time of the task.
     *
     * @param updatedDateTime the new date and time to be assigned to the deadline task.
     */
    public abstract void setDateTime(LocalDateTime updatedDateTime);

    /**
     * Sets the time of the task
     *
     * @param updatedDeadlineTime the new time to be assigned to the deadline task.
     */
    public abstract void setTime(LocalTime updatedDeadlineTime);

    /**
     * Sets the start time of the event
     *
     * @param updatedEventStartTime the new start time to be assigned to the event.
     */
    public abstract void setStartTime(LocalTime updatedEventStartTime);

    /**
     * Sets the end time of the event
     *
     * @param updatedEventEndTime the new end time to be assigned to the event.
     */
    public abstract void setEndTime(LocalTime updatedEventEndTime);


    /**
     * Sets the task description for a deadline/event.
     *
     * This method updates the task description with the provided new value,
     * appending it to the existing description along with the deadline time.
     * This ensures that the task description stored in the storage file
     * reflects the most current date or time values.
     *
     * @param newValue the new date or time to be appended to the task description.
     */

    public abstract void setDesc(String newValue);

}
