import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Retrieves the internal task list
     * @return Task list as an ArrayList<Task>
     */
    public ArrayList<Task> retrieveTasks() {
        return tasks;
    }

    /**
     * Adds a task into the task list
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the number of tasks in the task list.
     * @return Number of tasks in task list
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Gets the task in the task list based on the number.
     * Note that the number begins from one, unlike an index.
     * @param number A number, starting from 1
     * @return The task
     */
    public Task getTask(int number) {
        return tasks.get(number - 1);
    }

    /**
     * Removes the task in the task list based on the number.
     * Note that the number begins from one, unlike an index.
     * @param number A number, starting from 1
     * @return The task removed
     */
    public Task removeTask(int number) {
        return tasks.remove(number - 1);

    }

    /**
     * Marks the task in the task list based on the number.
     * Note that the number begins from one, unlike an index.
     * @param number A number, starting from 1
     * @return The task
     */
    public Task markTask(int number) {
        Task task = this.getTask(number);
        task.setCompletionStatus(true);
        return task;
    }

    /**
     * Unmark the task in the task list based on the number.
     * Note that the number begins from one, unlike an index.
     * @param number A number, starting from 1
     * @return The task
     */
    public Task unmarkTask(int number) {
        Task task = this.getTask(number);
        task.setCompletionStatus(false);
        return task;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= this.getNumberOfTasks(); i++) {
            Task task = this.getTask(i);
            result.append(i).append(". ").append(task.toString()).append("\n");
        }
        return result.toString();
    }
}
