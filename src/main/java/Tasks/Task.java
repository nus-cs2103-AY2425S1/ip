package Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * The Task class is an abstract representation of a task. It includes methods to get and set
 * the task's description, completion status, and unique identifier. The class also defines
 * abstract methods for setting dates and times specific to different types of tasks,
 * such as deadlines and events.
 *
 * Subclasses that extend Task must provide concrete implementations for handling dates and times.
 * This class supports updating the task description and completion status, as well as managing
 * time-sensitive details for various task types.
 *
 * Key functionalities include:
 * - Modifying task descriptions.
 * - Marking tasks as complete or incomplete.
 * - Abstract methods to manage deadlines and events (dates, start/end times).
 *
 * Subclasses must implement the following abstract methods:
 * - setDate1(LocalDate newDate): Update the primary date of the task.
 * - setDate2(LocalDate newDate): Update a secondary date, if applicable.
 * - setTime(LocalTime newTime): Set the task's time (if relevant).
 * - setEventEndTime(LocalTime newTime): Update the end time of an event task.
 * - setEventStartTiming(LocalTime newTime): Update the start time of an event task.
 */

public abstract class Task {
    //var that describes task
    protected String desc;
    //var that stores status of task
    protected boolean isDone;

    protected LocalDate localDate;

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

    public void setTaskDesc(String newValue) {
        this.desc = newValue;
    }


    //Abstract method to update the date for the task
    public abstract void setDate1(LocalDate newDate);
    public abstract void setDate2(LocalDate newDate);

    //Abstract method to update the time for the deadline task
    public abstract void setTime(LocalTime newTime);

    //Abstract method to update the time for the event task
    public abstract void setEventEndTime(LocalTime newTime);
    public abstract void setEventStartTiming(LocalTime newTime);
}
