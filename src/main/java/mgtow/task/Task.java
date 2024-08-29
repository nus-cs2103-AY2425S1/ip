package mgtow.task;

import java.time.LocalDate;

/**
 * Abstract base class representing a task in the MGTOW application.
 * This class provides common functionality for all types of tasks.
 */
public abstract class Task {
    private String desc;
    private String type;
    private boolean done;

    /**
     * Constructs a new Task with the given description and type.
     *
     * @param desc The description of the task.
     * @param type The type of the task.
     */
    public Task(String desc, String type){
        this.desc = desc;
        this.type = type;
        this.done = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone(){
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone(){
        this.done = false;
    }

    public String getStatus(){
        return (done ?  "X" : " ");
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

    @Override
    public String toString(){
        return String.format("[%s][%s] %s", this.type, this.getStatus(), this.desc);
    }
}
