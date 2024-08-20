/**
 * List of tasks.
 */
public class TaskList {
    private Task[] tasks;
    private int numberOfTasks;

    public TaskList() {
        this.tasks = new Task[100];
        this.numberOfTasks = 0;
    }

    /**
     * Adds a task to the list.
     *
     * @param description Description of the task.
     */
    public void add(String description) {
        this.tasks[numberOfTasks++] = new Task(description);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numberOfTasks; i++) {
            output.append(String.format("%d. %s \n", i + 1, this.tasks[i]));
        }

        return output.toString();
    }

}
