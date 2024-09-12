package ai.task;

import java.util.Objects;

/**
 * Class that has a status and description.
 */
public class Task {
    private boolean isDone = false;
    protected String description;

    public Task(String description) {
        this.description = description;
    }

    /**
     * Creates a new Task object.
     *
     * @param description String to be stored as description.
     * @param isDone whether the task is done.
     */
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
     * Returns task in the format of [ ] description.
     */
    @Override
    public String toString() {
        return getStatus() + description;
    }

    /**
     * Formats the Task for writing into the .txt file.
     *
     * @return String in the format of "1/0 | {description}".
     */
    public String stringData() {
        int isDoneNum = isDone ? 1 : 0;
        return isDoneNum + " | " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task castedObj = (Task) obj;
            return this.isDone == castedObj.isDone && Objects.equals(this.description, castedObj.description);
        }
        return false;
    }
}
