package duch.task;

public abstract class Task {
    String task;
    boolean isDone;

    public Task(String task, boolean done) {
        this.task = task;
        isDone = done;
    }

    public Task(String task) {
        this.task = task;
        isDone = false;
    }
    
    /**
     * To mark a task as done.
     * 
     * @param val
     */
    public void setDone(boolean val) {
        isDone = val;
    }

    /**
     * Get the string description of the task.
     * 
     * @return
     */
    public String getTask() {
        return task;
    }

    /**
     * To check whether the task is mark as done or not.
     * 
     * @return
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task.
     */
    public abstract String toString();

    public abstract String toStore();
}
