package tick.storage;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tick.exceptions.TickException;
import tick.tasks.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param checklist List of tasks.
     */
    public TaskList(ArrayList<Task> checklist) {
        this.tasks = checklist;
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks Tasks to be added to the TaskList.
     */
    public TaskList(Task... tasks) {
        this.tasks = new ArrayList<>();
        for (Task task : tasks) {
            this.tasks.add(task);
        }
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null.";
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index Index of the task to be deleted.
     * @return Task that was deleted.
     */
    public Task deleteTask(int index) throws TickException {
        try {
            return this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TickException("Invalid task number! Please enter a valid task number.");
        }
    }

    /**
     * Gets a task from the TaskList.
     *
     * @param index Index of the task to be retrieved.
     * @return Task that was retrieved.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked as done.
     * @return Task that was marked as done.
     */
    public Task markTaskAsDone(int index) throws TickException {
        try {
            Task doneTask = this.tasks.get(index);
            doneTask.markAsDone();
            return doneTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TickException("Invalid task number! Please enter a valid task number.");
        }
    }

    /**
     * Marks a task as undone.
     *
     * @param index Index of the task to be marked as undone.
     * @return Task that was marked as undone.
     */
    public Task markTaskAsUndone(int index) throws TickException {
        try {
            Task undoneTask = this.tasks.get(index);
            undoneTask.markAsUndone();
            return undoneTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TickException("Invalid task number! Please enter a valid task number.");
        }
    }

    /**
     * Gets all tasks in the TaskList.
     *
     * @return ArrayList of tasks in the TaskList.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        Stream<Task> taskStream = this.tasks.stream();
        ArrayList<Task> tasksWithKeyword = taskStream.filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        return tasksWithKeyword;
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
