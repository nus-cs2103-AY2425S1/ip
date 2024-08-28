package joe.tasks;

public class Task {
    private final String description;
    private boolean done;

    /**
     * Constructor for Task.
     *
     * @param description a String describing the Task
     * @throws IllegalArgumentException if description.isEmpty()
     */
    public Task(String description) throws IllegalArgumentException {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("You can't do a non-existent task.");
        }

        this.description = description;
        this.done = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return a String representing the description of the task
     */
    public String saveRepr() {
        return String.format("%d | %s", done ? 1 : 0, description);
    }


    /**
     * Sets the status of the task.
     *
     * @param done a boolean that determines whether a task is done
     */
    public void setDone(boolean done) {
        this.done = done;
    }


    /**
     * Returns a formatted String that represents the done and description fields of the Task
     *
     * @return a String representation of the Task object
     */
    @Override
    public String toString() {
        char mark = done ? 'X' : ' ';
        return String.format("[%c] %s", mark, description);
    }
}
