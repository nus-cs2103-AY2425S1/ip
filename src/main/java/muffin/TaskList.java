package muffin;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks. It provides methods to add, delete, mark as done,
 * unmark as done, and retrieve tasks, as well as to list all tasks or matching tasks in the list.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private ArrayList<Task> list;

    /**
     * Constructs a new TaskList with the specified list of tasks.
     *
     * @param list An ArrayList of tasks to initialize the task list with.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @return The task that was marked as done.
     */
    public Task mark(int index) {
        Task t = list.get(index);
        t.isDone = true;
        return t;
    }

    /**
     * Unmarks the task at the specified index as not done.
     *
     * @param index The index of the task to unmark as not done.
     * @return The task that was unmarked as not done.
     */
    public Task unmark(int index) {
        Task t = list.get(index);
        t.isDone = false;
        return t;
    }

    /**
     * Returns a formatted String representation of the elements in the list.
     *
     * @return A String representing the elements of the list, formatted with their positions.
     */
    public String list() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return result.toString();
    }


    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     */
    public Task delete(int index) {
        Task t = list.get(index);
        list.remove(index);
        return t;
    }

    /**
     * Adds a new task to the list.
     *
     * @param t The task to add to the list.
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Finds tasks in the list whose descriptions contain the specified keyword.
     *
     * @param keyword The keyword to search for within each task's description.
     * @return A formatted String of tasks whose descriptions contain the keyword,
     *         or an empty String if no tasks match the search.
     */
    public String find(String keyword) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task: list) {
            if (task.description.contains(keyword)) {
                newList.add(task);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < newList.size(); i++) {
            result.append(i + 1).append(". ").append(newList.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return an ArrayList of Task objects representing the current list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int length() {
        return list.size();
    }
}
