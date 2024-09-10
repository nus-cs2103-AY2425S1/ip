package talkabot.task;

import java.util.ArrayList;

import talkabot.Ui;

/**
 * TaskList class contains list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds task to list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns current number of tasks in list.
     *
     * @return Total number of tasks.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns task based on number.
     *
     * @param n task number to retrieved.
     * @return Task.
     */
    public Task get(int n) {
        return this.taskList.get(n);
    }

    /**
     * Deletes task based on number.
     *
     * @param n task number to be deleted.
     * @return Task being deleted.
     */
    public Task delete(int n) {
        return this.taskList.remove(n);
    }

    /**
     * Finds all tasks that match the keyword
     * @param input keyword
     */
    public TaskList find(String input) {
        TaskList matches = new TaskList();
        for (Task task : this.taskList) {
            if (task.description.contains(input)) {
                matches.add(task);
            }
        }
        return matches;
    }
}
