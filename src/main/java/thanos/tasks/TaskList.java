package thanos.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import thanos.storage.IStorage;

/**
 * Manages a list of tasks and handles operations related to task management.
 * <p>
 * The {@code TaskList} class provides methods to manage tasks, including marking,
 * unmarking, adding, removing tasks, and finding tasks by date. It also interacts
 * with an {@code IStorage} instance to load and save tasks.
 * </p>
 */

public class TaskList {
    /**
     * The list of tasks managed by this {@code TaskList}.
     */
    private final ArrayList<Task> tasks;

    /**
     * The storage interface used for loading and saving tasks.
     */
    private final IStorage storage;

    /**
     * Constructs a {@code TaskList} with the specified storage.
     * <p>
     * This constructor initializes the {@code TaskList} by loading tasks from
     * the provided {@code IStorage} instance.
     * </p>
     *
     * @param storage the {@code IStorage} instance used for loading and saving tasks.
     */
    public TaskList(IStorage storage) {
        this.tasks = storage.load();
        this.storage = storage;
    }

    /**
     * Returns the list of tasks managed by this {@code TaskList}.
     *
     * @return an {@code ArrayList} containing the tasks in this {@code TaskList}.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Returns the size of the task list, which is the number of tasks currently stored in this {@code TaskList}.
     *
     * @return the number of tasks in this {@code TaskList}.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Marks the task at a specified index as done, and saves the updated list to the storage.
     *
     * @param index the index of the task to mark as done.
     * @return the marked {@code Task} instance.
     */
    public Task mark(int index) {
        Task task = this.tasks.get(index);
        task.setDone(true);
        this.storage.save(this.tasks);
        return task;
    }

    /**
     * Unmarks the task at a specified index as not done, and saves the updated list to the storage.
     *
     * @param index the index of the task to unmark.
     * @return the unmarked {@code Task} instance.
     */
    public Task unmark(int index) {
        Task task = this.tasks.get(index);
        task.setDone(false);
        this.storage.save(this.tasks);
        return task;
    }

    /**
     * Adds the specified {@code Task} to the task list, and saves the updated list to the storage.
     *
     * @param task the {@code Task} instance to add to the list.
     * @return the added {@code Task} instance.
     */
    public Task add(Task task) {
        this.tasks.add(task);
        this.storage.save(this.tasks);
        return task;
    }

    /**
     * Removes a task at the specified index from the task list, and saves the updated list to the storage.
     *
     * @param index the index of the task to remove.
     * @return the removed {@code Task} instance.
     */
    public Task remove(int index) {
        Task task = this.tasks.remove(index);
        this.storage.save(this.tasks);
        return task;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword used to search for matching tasks in the task list.
     * @return An {@code ArrayList} of tasks that contain the keyword in their description.
     */
    public ArrayList<Task> find(String keyword) {
        return this.tasks.stream()
                .filter(task -> tasks.toString().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a list of tasks that match on the specified date, as determined by each task's {@code checkDate} method.
     *
     * @param date the date to match tasks against.
     * @return an {@code ArrayList} containing tasks that match the specified date.
     */
    public ArrayList<Task> findByDate(LocalDateTime date) {
        return this.tasks.stream()
                .filter(task -> task.checkDate(date))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
