package megamind.task;

import java.util.ArrayList;

/**
 * The `List` class represents a collection of tasks.
 * It provides methods to add, delete, mark, and find tasks within the list.
 * The class also includes methods to get the size of the list and return its string representation.
 */
public class List {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for the List class.
     */
    public List(ArrayList<Task> tasks) {
        ArrayList<Task> newTasks = new ArrayList<>();

        // Handles recursion of tasks
        tasks.forEach(task -> handleRecursion(newTasks, task));
        tasks.addAll(newTasks);
        this.tasks = tasks;
    }

    /**
     * Handles the recursion of tasks.
     * If the task is an instance of `RecurringTask`, it handles the recurrence and adds the new task to the list.
     *
     * @param list The list to which the recurring tasks are added.
     * @param t    The task to be checked and handled for recurrence.
     */
    public void handleRecursion(ArrayList<Task> list, Task t) {
        if (t instanceof RecurringTask) {
            Task recur = ((RecurringTask) t).handleRecurringTask();
            if (recur != null) {
                list.add(recur);
            }
            handleRecursion(list, recur);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task object that is to be added to the array list.
     */
    public void add(Task task) {
        ArrayList<Task> newTasks = new ArrayList<>();
        newTasks.add(task);
        handleRecursion(newTasks, task);
        tasks.addAll(newTasks);
    }

    /**
     * Returns the String representation of the list.
     *
     * @return String representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        if (tasks.isEmpty()) {
            return "There are no tasks in your list.";
        }

        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked as done.
     * @return True if the task is successfully marked as done, false otherwise.
     */
    public boolean markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param index Index of the task to be marked as not done.
     * @return True if the task is successfully marked as not done, false otherwise.
     */
    public boolean markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of task at the specified index.
     * This is to ensure that the task is not modified unintentionally
     *
     * @param index Index of the task to be returned.
     * @return Task at the specified index.
     */
    public String get(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Returns the size of the list.
     *
     * @return Size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of the task to be deleted.
     * @return Task if the task is successfully deleted, null otherwise.
     */
    public Task delete(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task t = tasks.get(index);
            tasks.remove(index);
            return t;
        } else {
            return null;
        }
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param keyword Keyword to be searched for in the tasks.
     * @return List of tasks that contain the keyword.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }

        return foundTasks.isEmpty()
                ? "No tasks found."
                : "Here are the found tasks\n" + new List(foundTasks);
    }

    /**
     * Returns the list of tasks.
     * Method is only used for the saving of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
