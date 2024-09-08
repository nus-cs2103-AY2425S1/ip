package nerf.task;

/**
 * Task superclass
 */
public abstract class Task {
    private final String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
        assert !description.equals("") : "Description should not be empty.";
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /** 
     * Sets task completion to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /** 
     * Sets task completion to false.
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
    public boolean isKeywordMatched(String keyword) {
        return this.description.contains(keyword);
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns format for saving to file.
     * 
     * @return string format of task.
     */
    public String getSaveFormat() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns format for printing.
     * 
     * @return string format of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

}