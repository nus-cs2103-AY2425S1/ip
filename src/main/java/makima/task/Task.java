package makima.task;

/**
 * Abstract class task
 */
public abstract class Task {

    private String name = "";
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new task
     *
     * @param name
     * @param isDone
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
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

        return output + " " + name;
    }

    /**
     * Converts all relevant data of the task to a string for saving purposes.
     *
     * @return string representing the task
     */
    public String toFileString() {
        return String.format("%s\n%s\n", name, isDone);
    }

}
