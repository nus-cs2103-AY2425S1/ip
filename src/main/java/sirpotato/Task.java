package sirpotato;

/**
 * Class to represent all Task objects
 * Holds description and completion of the task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates Task object with given description
     * @param description Description of the task to create
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon indicating whether a task is complete
     *
     * @return Status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }

    /**
     * Displays the description of the task and returns the description
     *
     * @return The description in string format
     */
    public String displayDescription() {
        return description;
    }

    public void setCompletion(boolean done) {
        this.isDone = done;
    }

    public boolean getCompletion() {
        return isDone;
    }

    @Override
    public String toString() {
        return (getStatusIcon() + " " + displayDescription());
    }

    /**
     * Returns whether the task description contains a certain string
     * The search is case-sensitive
     *
     * @param searchString the string we wish to search for
     * @return true if the description contains the string
     */
    public boolean containsString(String searchString) {
        return description.contains(searchString);
    }

    public boolean isDeadline() {
        return false;
    }
}
