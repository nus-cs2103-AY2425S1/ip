package ratchet.task;

import java.util.ArrayList;

/**
 * Class representing a task list.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Adds task to the task list.
     *
     * @param task Task to be added into the task list.
     */
    public void addTask(Task task) {
        super.add(task);
    }

    /**
     * Deletes task from the task list.
     *
     * @param i The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int i) {
        return super.remove(i);
    }

    /**
     * Marks task from the task list.
     *
     * @param i The index of the task to be marked.
     * @return The task that was marked.
     */
    public Task markTask(int i) {
        super.get(i).markAsDone();
        return super.get(i);
    }

    /**
     * Unmarks task from the task list.
     *
     * @param i The index of the task to unmarked.
     * @return The task that was unmarked.
     */
    public Task unmarkTask(int i) {
        super.get(i).markAsNotDone();
        return super.get(i);
    }

    /**
     * Returns a task list that contains only the keyword.
     *
     * @param keyword The keyword to filter the task list.
     * @return A task list that contains only the keyword.
     */
    public TaskList filter(String keyword) {
        TaskList filtered = new TaskList();
        for (Task task : this) {
            if (task.isMatch(keyword)) {
                filtered.add(task);
            }
        }
        return filtered;
    }

    @Override
    public String toString() {
        if (super.isEmpty()) {
            return "You have no tasks currently";
        } else if (super.size() == 1) {
            return "You have 1 task currently";
        } else {
            return "You have " + super.size() + " tasks currently";
        }
    }
}
