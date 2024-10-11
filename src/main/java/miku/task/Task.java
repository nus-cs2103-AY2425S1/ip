package miku.task;

import miku.utility.Priority;

/**
 * Represents a Task, which is a abstract parent class of all other task types.
 */
public abstract class Task {
    private String desc;
    private Boolean isDone = false;
    private String done = " ";
    private Priority priority = Priority.MEDIUM;

    public Task(String desc) {
        this.desc = desc;
    }

    /**
     * Instantiates a task object.
     *
     * @param desc
     * @param isDone
     */
    public Task(String desc, boolean isDone, Priority priority) {
        this.desc = desc;
        this.isDoneInit(isDone);
        this.priority = priority;
    }

    /**
     * If the value is read from the txt file, change the value accordingly
     *
     * @param isDone
     */
    public void isDoneInit(boolean isDone) {
        this.isDone = isDone;
        if (isDone) {
            done = "X";
        } else {
            done = " ";
        }
    }

    /**
     * Mark the task as done
     */
    public void markDone() {
        this.isDone = true;
        done = "X";
    }

    /**
     * Unmark the task, changing it to undone
     */
    public void markUndone() {
        isDone = false;
        done = " ";
    }

    /**
     * Return the isDone description after marking.
     * @return Response
     */
    public String doneStringValue() {
        if (isDone) {
            return "Nice! I've marked this task as done:\n[" + done + "] " + desc;
        } else {
            return "Ok! I've marked this task as not done yet:\n[" + done + "] " + desc;
        }
    }

    public String getDesc() {
        return desc;
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public abstract String storeValue();

    public String stringValue() {
        return ("[" + done + "] " + desc);
    }
    public void setPriority(Priority newPriority) {
        this.priority = newPriority;
    }
    public Priority getPriority() {
        return this.priority;
    }
}
