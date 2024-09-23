package donk.task;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a list of tasks
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructor for the TaskList class that initializes the task list with
     * the provided list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Default constructor for the TaskList class that initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index The index of the task to remove.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */

    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param t The Task object to be added to the list.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Search for tasks in the tasklist
     *
     * @param searchTerm
     * @return list of matching tasks
     */
    public TaskList find(String searchTerm) {
        List<Task> filteredList = this.tasks
                .stream()
                .filter(t -> t.getDescription().contains(searchTerm))
                .toList();
        return new TaskList(filteredList);
    }

    /**
     * sorts tasklist without mutating original
     * @return new sorted tasklist
     */
    public TaskList sorted() {

        List<Task> sortedList = this.tasks
                .stream()
                .sorted((Task t1, Task t2) -> t1.compareTo(t2))
                .toList();
        return new TaskList(sortedList);
    }

    /**
     * return string representation of tasklist
     * @return String list of tasks
     */
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "You've got not tasks yet bro. Add todo, deadline, or event tasks.";
        }

        String tasksString = "";

        for (int i = 1; i <= tasks.size(); i++) {
            tasksString += "\n" + i + ": " + this.getTask(i - 1).toString();
        }

        return tasksString;
    }
}
