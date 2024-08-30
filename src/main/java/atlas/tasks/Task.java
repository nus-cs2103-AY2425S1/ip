package atlas.tasks;

public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * @param name
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     *
     */
    public void setIsDone() {
        this.isDone = true;
    }

    /**
     *
     */
    public void setIsNotDone() {
        this.isDone = false;
    }

    /**
     * @return
     */
    public String toFileString() {
        String marked = this.isDone ? "1" : "0";
        return String.format("| %s | %s", marked, this.name);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        String marked = this.isDone ? "X" : " ";
        return String.format("[%s] %s", marked, this.name);
    }
}
