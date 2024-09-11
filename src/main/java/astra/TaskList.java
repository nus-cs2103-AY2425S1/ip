package astra;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;

import astra.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(Collection<? extends Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the list of tasks in text file format.
     *
     * @return List of tasks in text file format.
     */
    public String toTextFile() {
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            text.append(task.toTextFile()).append('\n');
        }
        return text.toString();
    }

    @Override
    public String toString() {
        int len = tasks.size();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < len; i++) {
            text.append(String.format("%d. %s \n", i + 1, tasks.get(i)));
        }
        return text.toString();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    private Task get(int index) throws AstraException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    private Task remove(int index) throws AstraException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of the task to be deleted.
     * @return Task that was deleted.
     * @throws AstraException If the index is invalid.
     */
    public Task delete(int index) throws AstraException {
        return this.remove(index);
    }

    /**
     * Marks a task as done or not done.
     *
     * @param index Index of the task to be marked.
     * @param done Whether the task is done.
     * @return Task that was marked.
     * @throws AstraException If the index is invalid.
     */
    public Task markAsDone(int index, boolean done) throws AstraException {
        Task t = this.get(index);
        t.setDone(done);
        return t;
    }

    public int length() {
        return tasks.size();
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing tasks that contain the keyword.
     */
    public TaskList find(String keyword) {
        return new TaskList(
                tasks.stream()
                        .filter(task -> task.hasKeyword(keyword))
                        .collect(toList())
        );
    }

    /**
     * Sorts the tasks by their deadline.
     * Tasks without a deadline are placed at the end.
     *
     * @return A TaskList containing the sorted tasks.
     */
    public TaskList sortByDeadline() {
        return new TaskList(
                tasks.stream()
                        .sorted()
                        .collect(toList())
        );
    }
}
