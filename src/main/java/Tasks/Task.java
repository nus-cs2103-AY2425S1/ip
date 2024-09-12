package Tasks;

public abstract class Task {
    //var that describes task
    protected String desc;
    //var that stores status of task
    protected boolean isDone;

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
}
