package taskpack;

/**
 * Parent class for tasks, has its own subclass tasks.
 */
public class Task {
    private boolean isCompleted;
    private String name;

    /**
     * Constructor for Task, usually gets called by subclasses.
     * @param name Name of the task.
     */
    public Task(String name) {
        this.isCompleted = false;
        this.name = name;
    }

    public Task(String name, boolean isCompleted) {
        this.isCompleted = isCompleted;
        this.name = name;
    }

    protected void markAsCompleted() {
        this.isCompleted = true;
    }

    protected void markAsIncomplete() {
        this.isCompleted = false;
    }
    @Override
    public String toString() {
        String box;
        if (this.isCompleted) {
            box = "[X]";
        } else {
            box = "[ ]";
        }
        return box + " " + this.name;
    }

    /**
     * Returns task completion status.
     * @return Boolean value on whether task is completed.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns task name.
     * @return Task name as a String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a string that is more easily parseable by Parser when file is read upon start.
     * It takes the current task traits and stores them into a string. Similar to toString
     * method. Uses polymorphism, so this method body is a filler one, actual implementation
     * in the task subclasses.
     * @return The String to be saved into the write file
     */
    public String toParseableString() {
        return "hello";
    }
}
