package nerf.task;

/**
 * Task superclass
 */
public abstract class Task {
    private final String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }
    public Task(String description,boolean status) {
        this.description = description;
        this.isDone = status;
    }

    /** 
     * Set task completion to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /** 
     * Set task completion to false.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns true if description has keyword in it.
     * Returns false otherwise.
     * 
     * @param keyword to check.
     * @return true if keyword in description.
     */
    public boolean matchSearch(String keyword) {
        return this.description.contains(keyword);
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Return format for saving to file.
     * 
     * @return string format of task.
     */
    public String getSaveFormat() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Return format for printing.
     * 
     * @return string format of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

}