package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

/**
 * Represents a list of tasks.
 * This class provides methods to manage tasks in a list, including adding, removing, and retrieving tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The initial list of tasks to populate the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList by its index.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed from the list.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }


    /**
     * Finds and returns a list of tasks whose descriptions contain the specified keyword.
     *
     * <p>This method iterates through all tasks in the task list and checks if
     * the description of each task contains the provided keyword. If a task's
     * description contains the keyword, it is added to the list of found tasks.</p>
     *
     * @param keyword the keyword to search for in the task descriptions.
     * @return a list of tasks that contain the specified keyword in their descriptions.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns a list of tasks that have dates associated with them, sorted
     * by date in ascending order.
     *
     * <p>This method filters the tasks to include only those that have a
     * non-null date (e.g., {@code Deadline} and {@code Event} tasks).
     * The tasks are then sorted based on their associated date, from the
     * earliest to the latest.</p>
     *
     * @return a list of tasks with dates, sorted in ascending order by date.
     */
    public List<Task> getTasksSortedByDate() {
        List<Task> dateTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDate() != null) {
                dateTasks.add(task);
            }
        }
        dateTasks.sort(Comparator.comparing(Task::getDate));
        return dateTasks;
    }

    /**
     * Returns a list of all tasks, sorted by their descriptions in
     * lexicographical order.
     *
     * <p>This method sorts tasks based on their descriptions, making it
     * easier to view tasks in alphabetical order.</p>
     *
     * @return a list of tasks sorted by their descriptions.
     */
    public List<Task> getTasksSortedByDescription() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparing(Task::getDescription, String.CASE_INSENSITIVE_ORDER));
        return sortedTasks;
    }
}


