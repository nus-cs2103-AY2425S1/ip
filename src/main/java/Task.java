/**
 * This class represents a task.
 * It provides contains the name of the task.
 */
public class Task {
    private String name;
    private boolean isDone;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    /**
     * mark task as done
     */
    public void markAsDone() {
        isDone = true;
    }
    /**
     * mark task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * print task description, including name and status
     *
     * @return returns the task description
     */
    public String printTask() {
        return (isDone ? "[X]" : "[ ]") + " " + name;
    }
}
