package ai.task;

/**
 * Class that has a status and description.
 */
public class Task {
    private boolean isDone = false;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Changes isDone status to true regardless of initial state.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Changes isDone status to false regardless of initial state.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Formats the completion status of the Task.
     *
     * @return String showing the completion status of the Task.
     */
    private String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    public boolean contains(String argument) {
        return description.contains(argument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getStatus() + description;
    }

    /**
     * Formats the Task for writing into the .txt file.
     *
     * @return String.
     */
    public String stringData() {
        int isDoneNum = isDone ? 1 : 0;
        return isDoneNum + " | " + description;
    }
}
