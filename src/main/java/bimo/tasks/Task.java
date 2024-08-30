package bimo.tasks;

public class Task {
    private boolean status = false;
    private String details;

    public Task(String details) {
        this.details = details;
    }

    public void markCompleted() {
        this.status = true;
    }
    public void markUncompleted() {
        this.status = false;
    }
    @Override
    public String toString() {
        String status = this.status ? "X": " ";
        return String.format("[%s] %s", status, this.details);
    }

    /**
     * Text formatter used to write a task into file
     * @return Returns the text format of status and description separated by |
     */
    public String getTaskText() {
        String state = this.status ? "1" : "0";
        return state + "|" + this.details;
    }

    /**
     * Returns details of tasks.
     * @return Description of task.
     */
    public String getDetails() {
        return this.details;
    }
}
