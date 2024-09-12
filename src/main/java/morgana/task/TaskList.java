package morgana.task;

import static morgana.common.Messages.MESSAGE_INVALID_TASK_NUMBER;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import morgana.exceptions.MorganaException;

/**
 * Represents a list of tasks, providing methods to add, remove, and
 * retrieve tasks from the list.
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with an initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index from the list and returns it.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task remove(int index) throws MorganaException {
        validateIndex(index);
        return tasks.remove(index);
    }

    /**
     * Updates the status of the task at the specified index.
     *
     * @param index The index of the task to update.
     * @param isDone {@code true} if the task is done, {@code false} otherwise.
     * @return The updated task.
     * @throws MorganaException If the index is out of bounds.
     */
    public Task updateTaskStatus(int index, boolean isDone) throws MorganaException {
        validateIndex(index);
        Task task = tasks.get(index);
        task.markAsDone(isDone);
        return task;
    }

    private void validateIndex(int index) throws MorganaException {
        if (index < 0 || index >= tasks.size()) {
            throw new MorganaException(MESSAGE_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Finds tasks in the list that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in the task description.
     * @return A formatted string listing the matching tasks.
     */
    public String find(String keyword) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                sb.append("%d. %s\n".formatted(i + 1, task));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("%d. %s\n".formatted(i + 1, tasks.get(i)));
        }
        return sb.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
