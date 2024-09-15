package lawrence.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks the user has specified to be tracked by the chatbot.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Default constructor. The number of items in the list will be
     * initialised to 0 by default.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor. The items in the list will be initialised to the specified
     * {@link Task} objects provided in the argument.
     *
     * @param tasks the array containing the {@link Task} objects to initialise
     *              the list with
     */
    public TaskList(Task[] tasks) {
        this.tasks = new ArrayList<>(List.of(tasks));
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the {@link Task} object to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list based on the number provided.
     * <p>
     * Task numbers start from one.
     * </p>
     *
     * @param taskNumber the index of the task to delete. Starts from 1
     * @return the {@link Task} object removed from the list
     * @throws IllegalArgumentException if the task number provided is out of bounds
     * @throws IllegalStateException if there are no tasks in the list
     */
    public Task deleteTask(int taskNumber) throws IllegalArgumentException, IllegalStateException {
        if (tasks.isEmpty()) {
            throw new IllegalStateException("There are no tasks that can be chosen for deletion.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IllegalArgumentException(
                    String.format("Task does not exist. Number must be within the range 1 to %s.", tasks.size()));
        }

        return tasks.remove(taskNumber - 1);
    }

    /**
     * Marks a task from the list as complete based on the number provided.
     * <p>
     * Task numbers start from one.
     * </p>
     *
     * @param taskNumber the index of the task to delete. Starts from 1
     * @return the {@link Task} object that was updated in the operation
     * @throws IllegalArgumentException if the task number provided is out of bounds
     * @throws IllegalStateException if there are no tasks in the list
     */
    public Task completeTask(int taskNumber) {
        if (tasks.isEmpty()) {
            throw new IllegalStateException("There are no tasks that can be chosen to be marked as complete.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IllegalArgumentException(
                    String.format("Task does not exist. Number must be within the range 1 to %s.", tasks.size()));
        }

        Task t = tasks.get(taskNumber - 1);
        assert t != null;
        t.setComplete(true);
        return t;
    }

    /**
     * Marks a task from the list as incomplete based on the number provided.
     * <p>
     * Task numbers start from one.
     * </p>
     *
     * @param taskNumber the index of the task to delete. Starts from 1
     * @return the {@link Task} object that was updated in the operation
     * @throws IllegalArgumentException if the task number provided is out of bounds
     * @throws IllegalStateException if there are no tasks in the list
     */
    public Task uncompleteTask(int taskNumber) {
        if (tasks.isEmpty()) {
            throw new IllegalStateException("There are no tasks that can be chosen to be marked as incomplete.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IllegalArgumentException(
                    String.format("Task does not exist. Number must be within the range 1 to %s.", tasks.size()));
        }

        Task t = tasks.get(taskNumber - 1);
        t.setComplete(false);
        return t;
    }

    /**
     * Matches the specified query with tasks descriptions and returns a
     * {@link TaskList} containing tasks which match the query.
     *
     * @param query the string to match task descriptions
     * @return a {@link TaskList} containing hits
     */
    public TaskList findTasks(String query) {
        List<Task> result = tasks.stream()
                .filter(task -> task.contains(query))
                .toList();

        return new TaskList(result.toArray(new Task[0]));
    }

    /**
     * Returns an array of {@link Task} objects stored in the list.
     *
     * @return an array of {@link Task} objects
     */
    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }

    /**
     * Returns the size of the current list.
     *
     * @return the size of the current list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d.%s%n", i + 1, tasks.get(i)));
        }

        // exclude the last newline character from getting printed
        return result.substring(0, result.length() - 2);
    }
}
