package xizi.chatbot.task;

import java.util.ArrayList;
import java.util.List;

// ArrayList implementation, auto-adjust the indexing after deletion

/**
 * Represents a list of tasks. Provides functionality to add, delete, mark, unmark,
 * and display tasks in the list.
 */
public class TaskList {

    private final List<Task> items;

    private int size;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.items = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Constructs a {@code TaskList} with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the {@code TaskList} with.
     */
    public TaskList(List<Task> tasks) {
        this.items = tasks;
        this.size = tasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param newTask The task to be added to the list.
     */
    public void addTask(Task newTask) {
        this.items.add(newTask);
        this.size += 1;
    }

    public int getSize() {
        return this.size;
    }

    public List<Task> getItems() {
        return this.items;
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        Task deleted = this.items.get(index);
        this.items.remove(index);
        this.size = this.size - 1;
        return deleted;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return A string representation of the marked task.
     */
    public String markTask(int index) {
        Task task = this.items.get(index);
        task.markDone();
        return task.toString();
    }

    /**
     * Unmarks the task at the specified index, setting its status to not done.
     *
     * @param index The index of the task to be unmarked.
     * @return A string representation of the unmarked task.
     */
    public String unmarkTask(int index) {
        Task task = this.items.get(index);
        task.unmark();
        return task.toString();
    }

    /**
     * Updates size of the task list based on its inner ArrayList.
     *
     */
    public void updateSize() {
        this.size = this.items.size();
    }

    /**
     * Returns a list of tasks with the given tag.
     *
     */
    public List<Task> searchByTag(String tag) {
        List<Task> tasksWithTag = new ArrayList<>();
        for (Task task : items) {
            if (task.hasTag(tag)) {
                tasksWithTag.add(task);
            }
        }
        return tasksWithTag;
    }





}
