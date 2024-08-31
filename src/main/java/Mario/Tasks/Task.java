package Mario.Tasks;

public class Task {
    protected boolean isCompleted;
    protected String name;

    public Task(String name) {
        this.isCompleted = false;
        this.name = name;
    }

    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns formatted name of the task.
     *
     */
    public String getName() {
        return this.name;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markUncompleted() {
        this.isCompleted = false;
    }

    public String getType() {
        return "";
    }

    /**
     * Returns task as a formatted name for storage.
     *
     */
    public String toFileFormat() {
        return "";
    }
}
