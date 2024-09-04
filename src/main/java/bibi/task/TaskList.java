package bibi.task;

import java.util.ArrayList;

/**
 * Represents the collection of tasks and its subtypes.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task with the given index from the list.
     *
     * @param index The index of the task. Starts from 1.
     * @return The task that was removed from the list.
     */
    public Task removeFromTaskList(int index) {
        if (index > tasks.size() || index <= 0) {
            return null;
        } else {
            return tasks.remove(index - 1);
        }
    }

    public void addToTaskList(Task t) {
        tasks.add(t);
    }

    /**
     * Prints the entire list of tasks in order of declaration.
     */
    public void printTaskList() {
        if (tasks.isEmpty()) {
            System.out.println("Good for you, nothing to do today :3");
            return;
        }

        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d: %s%n", i, tasks.get(i - 1));
        }
    }
}
