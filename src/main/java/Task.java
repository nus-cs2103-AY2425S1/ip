package main.java;

public class Task {
    private Boolean done;
    private String desc;

    public Task(String desc) {
        this.desc = desc;
        this.done = false;
    }

    /**
     * Marks this task as done/completed.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks this task as not done/completed.
     */
    public void markUndone() {
        this.done = false;
    }

    /**
     * Returns description of task.
     * @return task description
     */
    public String getDesc() {
        return done.equals(true) ? "[X] " + this.desc : "[ ] " + this.desc;
    }

}
