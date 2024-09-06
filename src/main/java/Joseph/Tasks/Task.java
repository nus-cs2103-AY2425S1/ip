package Joseph.Tasks;

/**
 * Abstract class that provides the skeleton for the Todo, Deadline and Event classes.
 */
public abstract class Task {
    private final String desc;
    private boolean isDone;

    /**
     * Initialises a new Task object
     * @param desc The description of the task. Should be passed in as a String.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }
    public String getDone() {
        return isDone ? "X" : " ";
    }
    public String getDesc() {
        return this.desc;
    }
    public String getDetails() {
        return this.desc;
    }
    public void setDone() {
        this.isDone = true;
    }
    public void unDone() {
        this.isDone = false;
    }
}
