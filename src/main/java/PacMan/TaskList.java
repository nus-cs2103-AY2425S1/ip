package pacman;

import java.util.ArrayList;

/**
 * Implements the list of <code>Task</code>. A <code>TaskList</code>
 * corresponds to a <code>ArrayList</code> that saves the list of task
 */
public class TaskList {
    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
    }

    /**
     * Add a task to the list
     *
     * @param task Task that to be added
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Change the status of a task
     *
     * @param index index of the task in the list
     * @param status updated status
     * @return the task that is updated
     */
    public Task toggleTask(int index, boolean status) {
        this.list.get(index).setMarkDone(status);
        return this.list.get(index);
    }

    /**
     * Delete a task
     *
     * @param index index of the task in the list
     * @return the task that is deleted
     */
    public Task deleteTask(int index) {
        Task deletedTask = this.list.get(index);
        this.list.remove(index);
        return deletedTask;
    }

    /**
     * Return a <code>String</code> that is readable and writeable by <code>Storage</code>
     *
     * @return a <code>String</code> that is readable and writeable by <code>Storage</code>
     */
    public String toFile() {
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < this.list.size(); index = index + 1) {
            output.append(this.list.get(index).toFile()).append("\n");
        }
        return output.toString();
    }

    public String findTask(String matcher) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int index = 0, findIndex = 0; index < this.list.size(); index = index + 1) {
            if (this.list.get(index).isMatched(matcher)) {
                findIndex = findIndex + 1;
                output.append(findIndex).append(". ").append(this.list.get(index).toString()).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Return the size of the list of task
     *
     * @return the size of the list of task
     */
    public int getSize() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int index = 0; index < this.list.size(); index = index + 1) {
            output.append(index + 1).append(". ").append(this.list.get(index).toString()).append("\n");
        }
        return output.toString();
    }
}
