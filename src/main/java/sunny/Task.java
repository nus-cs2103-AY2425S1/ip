package sunny;

/**
 * Represents information of tasks specified by user
 */
public class Task {
    // FIELDS-----------------------------
    String description;
    Boolean isDone;

    /**
     * Initialises description of tasks and set the status of completion
     * @param description describe the task, ie eat lunch
     * @param isDone if the tasks is completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Initialises description of task and set as not done
     * @param description describe the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setter (boolean x) {
        this.isDone = x;
    }

    public String getDescription() {
        return description;
    }

    public String getTimeline() {
        return null;
    }
    public String toString() {
        if (isDone) {
            return String.format("[X] %s", description);
        } else {
            return String.format("[] %s", description);
        }
    }


}
