package fence.tasklist;

import java.util.ArrayList;

import fence.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> items = new ArrayList<>();

    /**
     * Constructs a task list with the tasks stored in an arraylist.
     * @param items List of tasks stored in an arraylist.
     */
    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    /**
     * Returns the element at the specified position in the list.
     * @param i Index of task.
     * @return Task at index i.
     */
    public Task getTask(int i) {
        return items.get(i);
    }

    /**
     * Returns the number of elements in the list.
     * @return Size of the task list.
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Appends the given task to the end of the list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        items.add(task);
    }

    /**
     * Marks the task at the specified position within the list displayed as complete.
     * @param i Index of the task as given by the list printed by {@link fence.ui.Ui#list(TaskList)}.
     */
    public void mark(int i) {
        Task task = items.get(i - 1);
        task.complete();
    }

    /**
     * Marks the task at the specified position within the list displayed as incomplete.
     * @param i Index of the task as given by the list printed by {@link fence.ui.Ui#list(TaskList)}.
     */
    public void unmark(int i) {
        Task task = items.get(i - 1);
        task.undo();
    }

    /**
     * Removes the task at the specified position within the list displayed. Shifts any subsequent elements to the left.
     * @param i Index of the task as given by the list printed by {@link fence.ui.Ui#list(TaskList)}.
     */
    public void delete(int i) {
        items.remove(i - 1);
    }

    /**
     * Returns the subset of tasks whose description partially or fully matches the given keyword.
     * @param keyword Keyword to be searched.
     * @return Tasklist containing tasks whose description partially or fully matches the given keyword.
     */
    public TaskList find(String keyword) {
        TaskList res = new TaskList(new ArrayList<Task>());
        for (int i = 0; i < items.size(); i++) {
            Task curr = items.get(i);
            if (curr.containsKeyword(keyword)) {
                res.add(curr);
            }
        }
        return res;
    }

    /**
     * Returns the string representation of the tasklist. Each task is listed line by line, with index starting from 1.
     * @return String representation of tasklist.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < items.size(); i++) {
            res += i + 1 + ". " + items.get(i) + "\n";
        }
        return res;
    }

    /**
     * Returns the subset of incomplete deadline tasks that are due on the current day.
     * @return Tasklist containing incomplete deadline tasks that are due on the current day.
     */
    public TaskList findDue() {
        TaskList res = new TaskList(new ArrayList<Task>());
        for (int i = 0; i < items.size(); i++) {
            Task curr = items.get(i);
            if (curr.isDue() && !curr.isComplete()) {
                res.add(curr);
            }
        }
        return res;
    }
}
