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

    /**
     * Marks the task on the list as done.
     *
     * @param taskNumber Number of the task.
     */
    public Task markAsDone(int taskNumber) {
        this.tasks[taskNumber - 1].markAsDone();

        return this.tasks[taskNumber - 1];
    }

    /**
     * Marks the task on the list as undone.
     *
     * @param taskNumber Number of the task.
     */
    public Task markAsUndone(int taskNumber) {
        this.tasks[taskNumber - 1].markAsUndone();

        return this.tasks[taskNumber - 1];
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return List of tasks.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numberOfTasks; i++) {
            output.append(String.format("%d. %s \n", i + 1, this.tasks[i].toString()));
        }

        return output.toString();
    }

}
