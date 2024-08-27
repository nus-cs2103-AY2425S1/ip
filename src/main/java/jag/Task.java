package jag;

public class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {

    }

    /**
     * Return task instance description
     *
     * @return String representation of task description
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X] " : "[ ] ";
        return status + this.description;
    }

    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }

}
