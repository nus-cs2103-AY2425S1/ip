package task;

public class KorolevTask {
    private boolean status = false;
    private String name;

    /**
     * Constructs an object of KorolevTask.
     *
     * @param name description of the task
     */
    public KorolevTask(String name) {
        this.name = name;
    }

    /**
     * Marks the task and change its displayed message.
     */
    public void markTask() {
        this.status = true;
    }

    /**
     * Marks the task and change its displayed message.
     */
    public void unmarkTask() {
        this.status = false;
    }

    /**
     * Checks whether the task name contains the keyword
     *
     * @param keyword keys input by users
     * @return tests whether the description contains certain keywords
     */
    public boolean match(String keyword) {
        return this.name.contains(keyword);
    }

    /**
     * Overrides toString method in KorolevTask.
     *
     * @return string representation of Object
     */
    @Override
    public String toString() {
        return (this.status ? "[X]" : "[ ]") + " " + this.name;
    }
}
