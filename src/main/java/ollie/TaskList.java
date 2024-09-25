package ollie;

import java.util.ArrayList;
import java.util.stream.Collectors;

import ollie.exception.OllieException;
import ollie.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a tasklist object using a given arraylist of task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a tasklist object using an empty arraylist of task.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the size of the tasklist.
     */
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
     * @param index Index represents the indexition of the task to be removed within the list.
     * @return Task which has been removed.
     * @throws OllieException If the index given does not exist.
     */
    public Task delete(int index) throws OllieException {
        assert (tasks != null);
        if (!isIndexValid(index)) {
            throw new OllieException("Invalid Serial number given!");
        }
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return task;
    }

    /**
     * Remove the task in its ArrayList of Task as specified by the task and
     * return the task.
     *
     * @param task Task represents the task to be removed within the list.
     * @return Task which has been removed.
     * @throws OllieException If the index given does not exist.
     */
    public Task delete(Task task) throws OllieException {
        assert (tasks != null);
        int indexOfTask = tasks.indexOf(task);
        if (indexOfTask < 0) {
            throw new OllieException("Invalid task given!");
        }
        this.tasks.remove(indexOfTask);
        return task;
    }

    /**
     * Mark the task, in its ArrayList of Task specified by the index, as done
     * and return the task.
     *
     * @param index Index represents the index of the task in list to be mark as done.
     * @return Task which has been marked as done.
     */
    public Task markAsDone(int index) throws OllieException {
        assert (tasks != null);
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
     * @param index index reprsents the index of the task in list to be mark as undone.
     * @return Task which has been marked as done.
     */
    public Task markAsUndone(int index) throws OllieException {
        assert (tasks != null);
        if (!isIndexValid(index)) {
            throw new OllieException("Invalid serial number given!");
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     *  Filters the taskslist to get a new task list where all the task contains findQuery
     * @param findQuery desc to filter for
     */
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
        assert (tasks != null);
        return (index > -1 & index < tasks.size());
    }

    @Override
    public String toString() {
        assert (tasks != null);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            list.add(String.format("%d.%s%s", i + 1, tasks.get(i), i == tasks.size() - 1 ? "" : "\n"));
        }
        return String.join("", list);
    }
}
