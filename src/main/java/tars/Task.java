package tars;

public class Task {
    private String name;
    private boolean done;

    /**
     * Constructs a new Task with the specified name and completion status.
     *
     * @param name the name or description of the task.
     * @param done the completion status of the task; {@code true} if the task is done,
     *             {@code false} if the task is not done.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public void setUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return ("[" + (this.done ? "X" : " ") + "] " + this.name);
    }
}
