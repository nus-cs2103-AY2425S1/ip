package mgtow.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Abstract base class representing a task in the MGTOW application.
 * This class provides common functionality for all types of tasks.
 */
public abstract class Task implements Comparable<Task>{
    private String desc;
    private String type;
    private boolean isDone;

    /**
     * Constructs a new Task with the given description and type.
     *
     * @param desc The description of the task.
     * @param type The type of the task.
     */
    public Task(String desc, String type){
        this.desc = desc;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone(){
        this.isDone = false;
    }

    public String getStatus(){
        return (isDone ?  "X" : " ");
    }

    public String getType() {
        return this.type;
    }

    public String getDesc(){
        return this.desc;
    }

    /**
     * Checks if the task is scheduled for the given date.
     *
     * @param date The date to check.
     * @return true if the task is scheduled for the given date, false otherwise.
     */
    public abstract boolean isOnDate(LocalDate date);

    public abstract LocalDateTime getDateTime();

    @Override
    public String toString(){
        return String.format("[%s][%s] %s", this.type, this.getStatus(), this.desc);
    }
    @Override
    public int compareTo(Task other) {
        if (this.getDateTime() == null && other.getDateTime() == null) {
            return 0;
        }
        if (this.getDateTime() == null) {
            return 1; // Tasks without dates go to the end
        }
        if (other.getDateTime() == null) {
            return -1; // Tasks without dates go to the end
        }
        return this.getDateTime().compareTo(other.getDateTime());
    }
}
