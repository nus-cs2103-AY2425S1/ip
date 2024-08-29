package topaz.task;

import topaz.exception.InvalidStateException;

import java.util.ArrayList;

/**
 * Represents a task with a description and a completion status.
 * A {@link Task} object has a description and a boolean flag indicating whether the task is done.
 */
public class Task {
    protected String description;
    protected Boolean isDone = false;

    /**
     * Constructs a {@link Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a {@link Task} with the specified description and default completion status of {@code false}.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }


    /**
     * Parses and sets the completion status of the task based on the provided state.
     *
     * @param state The state to be parsed, should be "0" or "1".
     * @throws InvalidStateException If the state is invalid or cannot be parsed.
     */
    public void parseState(String state) throws InvalidStateException {
        try {
            int stateInt = Integer.parseInt(state);
            if (stateInt == 1) {
                this.setDone();
            }
        } catch (NumberFormatException e) {
            throw new InvalidStateException(state);
        }
    }

    @Override
    public String toString() {
        return description + " isDone: " + isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndo() {
        this.isDone = false;
    }

    public String toFileRecord() {
        return "type" + " | " + "state" + " | " + description;
    }

    public void addToList(ArrayList<Task> list) {
        list.add(this);
    }
}
