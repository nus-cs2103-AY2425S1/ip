package hoshi.task;

import java.util.ArrayList;

/**
 * TaskList class that handles 3 types of tasks
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks the initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes a task in the taskList given an index.
     *
     * @param index the index of the task to be deleted.
     */
    public void delete(int index) {

        this.tasks.remove(index);
    }

    public void setMark(int index) {
        this.tasks.get(index).setIsDone(true);
    }

    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            result.append(i + 1).append(". ").append(this.tasks.get(i).toString()).append("\n");
        }

        return result.toString();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
