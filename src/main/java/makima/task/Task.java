package makima.task;

/**
 * Abstract class task
 */
public abstract class Task {

    /**
     * Priority levels of task
     */
    public enum PriorityLevel {
        LOW,
        HIGH
    };

    private String name = "";
    private boolean isDone = false;
    private PriorityLevel priorityLevel = PriorityLevel.LOW;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new task, used for load purposes
     *
     * @param name
     * @param isDone
     */
    public Task(String name, boolean isDone, PriorityLevel priorityLevel) {
        this.name = name;
        this.isDone = isDone;
        this.priorityLevel = priorityLevel;
    }

    public boolean match(String search) {
        return name.toLowerCase().contains(search.toLowerCase());
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    /**
     * Converts all relevant data of the task to a string for printing purposes
     *
     * @return string representing the task
     */
    public String toString() {
        String output = "";

        if (isDone) {
            output += "[X]";
        } else {
            output += "[ ]";
        }
        switch (priorityLevel) {
        case HIGH:
            return output + " " + name.toUpperCase();
        case LOW:
            return output + " " + name.toLowerCase();
        default:
            return output + " " + name;
        }
    }

    /**
     * Converts all relevant data of the task to a string for saving purposes.
     *
     * @return string representing the task
     */
    public String toFileString() {
        return String.format("%s\n%s\n%s\n", name, isDone, priorityLevel);
    }

    /**
     * Set task priority.
     *
     * @param priorityLevel - 0,1
     */
    public void setPriority(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    /**
     * Get task priority
     *
     * @return task's priority
     */
    public PriorityLevel getPriority() {
        return this.priorityLevel;
    }

}
