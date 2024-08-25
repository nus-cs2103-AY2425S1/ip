package david.Task;

import java.util.List;

public class TaskList {

    List<Task> tasks;

    /**
     * Constructor for task list
     * @param t List of tasks
     */
    public TaskList(List<Task> t) {
        this.tasks = t;
    }

    /**
     * Adds a task to the array list
     * @param t task to add
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Returns the size of the arraylist
     * @return size of arraylist
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Removes a task from the array list
     * @param n index of the task to remove (0-indexed)
     */
    public void deleteTask(int n) {
        tasks.remove(n);
    }

    /**
     * Marks a task in the arraylist as done
     * @param n index of the task to mark (0-indexed)
     */
    public void markTaskAsDone(int n) {
        Task t = tasks.get(n);
        t.markAsDone();
    }

    /**
     * Marks a task in the arraylist as undone
     * @param n index of the task to mark (0-indexed)
     */
    public void markTaskAsUndone(int n) {
        Task t = tasks.get(n);
        t.markAsUnDone();
    }

    /**
     * Get a task from the arraylist
     * @param n index of the task to get (0-indexed)
     * @return Task at the specified index
     */
    public Task getTask(int n) {
        return this.tasks.get(n);
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "All is good :) There is no tasks for the time being.";
        }
        String str = "";
        for (int i = 0; i<tasks.size(); i++) {
            str += i+1 + ": " + tasks.get(i) + "\n";
        }
        return str;
    }
}
