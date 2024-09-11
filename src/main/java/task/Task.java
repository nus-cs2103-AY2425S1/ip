package task;

import exceptions.LightException;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) throws LightException {
        if (description.isEmpty()) {
            throw new LightException("Description cannot be empty");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public Task clone() {
        Task newTask = null;
        try {
            newTask = new Task(this.description);
        } catch (LightException e) {
            throw new RuntimeException(e);
        }
        newTask.isDone = this.isDone;
        return newTask;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task other = (Task) o;
            return this.description.equals(other.description) && this.isDone == other.isDone;
        }
        return false;
    }

}