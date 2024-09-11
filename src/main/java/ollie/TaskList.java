package ollie;

import ollie.exception.OllieException;
import ollie.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Add task
     *
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove the task in its ArrayList of Task as specified by the index and
     * return the task.
     *
     * @param index Index represents the position of the task to be removed within the list.
     * @return Task which has been removed.
     * @throws OllieException If the index given does not exist.
     */
    public Task delete(int index) throws OllieException {
        assert(tasks != null);
        if (!isIndexValid(index)) {
            throw new OllieException("Invalid Serial number given!");
        }
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return task;
    }

    /**
     * Mark the task, in its ArrayList of Task specified by the index, as done
     * and return the task.
     *
     * @param index Index represents the position of the task to be mark as done.
     * @return Task which has been marked as done.
     */
    public Task markAsDone(int index) throws OllieException {
        assert(tasks != null);
        if (!isIndexValid(index)) {
            throw new OllieException("Invalid Serial number given!");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Mark the task, in its ArrayList of Task specified by the index, as undone
     * and return the task.
     *
     * @param index Index reprsents the position of the task to be mark as undone.
     * @return Task which has been marked as done.
     */
    public Task markAsUndone(int index) throws OllieException{
        assert(tasks != null);
        if (!isIndexValid(index)) {
            throw new OllieException("Invalid Serial number given!");
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    public TaskList filterByString(String findQuery) {
        return new TaskList(this.tasks.stream().filter(task -> task.doesDescContain(findQuery)).collect(Collectors
                .toCollection(ArrayList::new)));
    }

    /**
     * Checks tasks can be indexed by the given index.
     *
     * @param index
     */
    private boolean isIndexValid(int index) {
        assert(tasks != null);
        return (index < 0 || index > tasks.size() - 1);
    }

    @Override
    public String toString() {
        assert(tasks != null);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            list.add(String.format("%d.%s%s", i + 1, tasks.get(i), i == tasks.size() - 1 ? "" : "\n"));
        }
        return String.join("", list);
    }
}
