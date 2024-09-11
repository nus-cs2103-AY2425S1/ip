package nether.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 * The {@code TaskList} class provides methods to manage a collection of {@code Task} objects,
 * such as adding, deleting, and retrieving tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with an initial list of tasks.
     * This method is used when we are loading tasks from data file.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a {@code Task} to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a {@code Task} from the task list by its index.
     *
     * @param index The index of the task to be removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a {@code Task} from the task list by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task with the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return A {@code List} of {@code Task} objects in the task list.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints out the task list along with its status (done or not done).
     */
    public String printList() {
        StringBuilder response = new StringBuilder();
        if (tasks.isEmpty()) { // guard case for 0 length list
            response.append("There are no tasks in your list");
            return response.toString();
        }
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < getSize(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    /**
     * Finds the tasks whose description matchers the input string.
     * @param searchString The input string given by user.
     * @return A list of tasks whose descriptions match the input string.
     */
    public TaskList searchTask(String searchString) {
        List<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < getSize(); i++) {
            if (this.getTask(i).getDescription().contains(searchString)) {
                matchingTasks.add(tasks.get(i));
            }
        }

        return new TaskList(matchingTasks);
    }

}
