package jag;

/**
 * Task Class store information about any type of tasks. It consist of
 * a description of the task and whether it is marked complete or isDone
 */
public class Task {
    protected String description;
    protected Boolean isDone;

    /**
     * A custom constructor to set the description of the given task
     *
     * @param description String representation of the description that has been
     *                    entered by the user
     */
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
