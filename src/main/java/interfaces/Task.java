package interfaces;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Stores Description and Status of a Task.
     *
     * @param description Description of class.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " (#" + this.tag + ")";
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public void setDone() {
        this.isDone = true;
    }

    public void setUnDone() {
        this.isDone = false;
    }

    public String loadString() {
        return this.description;
    }

}
