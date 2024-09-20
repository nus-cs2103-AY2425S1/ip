package diomon.task;

import java.util.ArrayList;

/**
 * The {@code TaskList} class manages a collection of {@link Task} objects.
 * It provides methods for adding, removing, marking, and unmarking tasks.
 * It also allows conversion of tasks to string representations suitable for display and storage.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with the given tasks.
     *
     * @param args One or more {@code Task} objects to initialize the list with.
     */
    public TaskList(Task... args) {
        this.tasks = new ArrayList<>();
        for (Task task : args) {
            this.add(task);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Performs a fuzzy search on the tasks in the list by checking if each task's description contains the given input string.
     * Returns a {@link TaskList} containing all tasks that match the input.
     *
     * @param input The string to search for in the task descriptions.
     * @return A {@link TaskList} containing tasks whose descriptions contain the input string.
     */
    public String fuzzyFind(String input) {
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(input)){
                results.append(String.format("%d. %s", i + 1, tasks.get(i)));
            }
        }
        return results.toString();
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param i The index of the task to be marked as completed.
     */
    public void mark(int i) {
        this.tasks.get(i).mark();
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param i The index of the task to be marked as incomplete.
     */
    public void unmark(int i) {
        this.tasks.get(i).unmark();
    }

    /**
     * Returns a string representation of the task list for display.
     * Each task is numbered and shows its type icon, status icon, and description.
     *
     * @return A string containing all tasks in a human-readable format.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++){
            Task t = tasks.get(i);
            result = result.concat(String.format("%d-> [%s][%s] %s\n", i + 1, t.getTypeIcon(), t.getStatusIcon(), t));
        }
        return result;
    }

    /**
     * Converts the task list to a string representation suitable for storage.
     * Each task is formatted as a string and separated by a newline.
     *
     * @return A string containing all tasks formatted for storage.
     */
    public String toStorageString() {
        String result ="";
        for (Task t : tasks) {
            result = result.concat(String.format("%s\n", t.toStorageString()));
        }
        return result;
    }
}
