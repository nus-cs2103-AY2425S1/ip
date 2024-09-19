package gallium.task;

import java.text.ParseException;

/**
 * The Task class represents a task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public static int count = 1;

    /**
     * Constructs a Task with the description. The task is initially not done.
     * 
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, whether it is done or not.
     * 
     * @return A string ("[X]" if done, "[ ]" if not done).
     */
    public String getStatusIcon() {
        return ((isDone ? "[X]" : "[ ]") + " ");
    }

    /**
     * Sets the completion status of the task.
     * 
     * @param isDone True if the task is completed, false if not.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task, including its status icon and
     * description.
     * 
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Returns the singular/plural form for the word "task".
     * 
     * @return "task" if count is 1, otherwise "tasks".
     */
    public static String taskCount() {
        return Task.count == 1
                ? "task"
                : "tasks";
    }

    /**
     * Returns the singular/plural form for the word "task" after deletion.
     * 
     * @return "task" if count is 2, otherwise "tasks".
     */
    public static String taskCountDelete() {
        return Task.count == 2
                ? "task"
                : "tasks";
    }

    /**
     * Returns the description of the task.
     * 
     * @return The description of the task.
     */
    public String getDesc() {
        return this.description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public void setBy(String message) throws ParseException {

    }

    public void setFrom(String message) throws ParseException {

    }

    public void setTo(String message) throws ParseException {

    }



}
