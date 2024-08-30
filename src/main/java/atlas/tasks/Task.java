package atlas.tasks;

public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Sets the isDone status to true.
     */
    public void setIsDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone status to false.
     */
    public void setIsNotDone() {
        this.isDone = false;
    }

    /**
     * @return String The format in which this task is stored in a file.
     */
    public String toFileString() {
        String marked = this.isDone ? "1" : "0";
        return String.format("| %s | %s", marked, this.name);
    }

    /**
     * @return String The format in which this task is shown in the ui.
     */
    @Override
    public String toString() {
        String marked = this.isDone ? "X" : " ";
        return String.format("[%s] %s", marked, this.name);
    }
}
