package joe.task;

import java.util.ArrayList;

/**
 * The {@code TaskList} class represents a collection of tasks and provides methods
 * to interact with this collection, such as adding, removing, marking, unmarking,
 * and finding tasks.
 */
public class TaskList {
    /** The list that contains all the tasks. */
    private final ArrayList<Task> taskList;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with an initial list of tasks.
     *
     * @param tasks An {@code ArrayList} of tasks to initialize the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(tasks);
    }

    /**
     * Converts the {@code TaskList} to an {@code ArrayList}.
     *
     * @return The {@code ArrayList} containing all the tasks.
     */
    public ArrayList<Task> toArrayList() {
        return this.taskList;
    }

    /**
     * Checks if the {@code TaskList} is empty.
     *
     * @return {@code true} if the task list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Adds a task to the {@code TaskList}.
     *
     * @param task The {@code Task} to be added to the list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the number of tasks in the {@code TaskList}.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Removes a task from the {@code TaskList} by its index.
     *
     * @param idx The index of the task to be removed (1-based index).
     */
    public void removeTask(int idx) {
        Task task = this.taskList.remove(idx - 1);
        System.out.printf("\tNoted. I've removed this task:\n\t  %s\n", task);
    }

    /**
     * Marks a task as done by its index.
     *
     * @param idx The index of the task to be marked as done (1-based index).
     */
    public void markTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.markDone();
        System.out.printf("\tNice! I've marked this task as done:\n\t  %s\n", task);
    }

    /**
     * Unmarks a task as not done by its index.
     *
     * @param idx The index of the task to be marked as not done (1-based index).
     */
    public void unmarkTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.unmarkDone();
        System.out.printf("\tOK, I've marked this task as not done yet:\n\t  %s\n", task);
    }

    /**
     * Finds and returns tasks that contain the specified query string.
     *
     * @param query The query string to search for within the task descriptions.
     * @return An {@code ArrayList} of tasks that match the query.
     */
    public ArrayList<Task> find(String query) {
        ArrayList<Task> matchedArr = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.contains(query)) {
                matchedArr.add(task);
            }
        }
        return matchedArr;
    }
}
