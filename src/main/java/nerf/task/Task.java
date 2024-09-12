package nerf.task;

/**
 * Task superclass
 */
public abstract class Task {
    private final String description;
    private boolean isDone = false;
    private TaskPriority priority;

    public Task(String description) {
        this.description = description;
        assert !description.equals("") : "Description should not be empty.";
    }
    public Task(String description, boolean isDone, String priority) {
        this.description = description;
        this.isDone = isDone;
        try{
            int priorityLevel = Integer.parseInt(priority);
            this.priority = TaskPriority.getPriorityLevel(priorityLevel);
        } catch (NumberFormatException e) {
            System.out.println("Unknown priority detected");
        }
    }

    public void setPriority(int priorityLevel) {
        this.priority = TaskPriority.getPriorityLevel(priorityLevel);
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
        String res = String.format("%d | %s | ", this.isDone ? 1 : 0, this.description);
        if (this.priority != null) {
            res += this.priority.getPriorityLevel();
        } else {
            res += "0";
        }
        return res;
    }

    /**
     * Returns format for printing.
     * 
     * @return string format of task.
     */
    @Override
    public String toString() {
        String res = String.format("[%s] %s", getStatusIcon(), this.description);
        if (this.priority != null) {
            res += String.format(" Priority: %s", this.priority);
        }
        return res;
        
    }

}