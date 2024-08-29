package topaz.task;

import topaz.exception.InvalidStateException;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected Boolean isDone = false;

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
    }

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
        return description;
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

}
