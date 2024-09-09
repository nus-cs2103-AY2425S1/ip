package spike.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import spike.exceptions.SpikeException;
import spike.tasks.Deadline;
import spike.tasks.Event;
import spike.tasks.Task;

/**
 * Represents the user's list of tasks.
 */
public class TaskList {
    private static final Comparator<Task> dateComparator = new Comparator<Task>() {
        @Override
        public int compare(Task t1, Task t2) {
            LocalDateTime date1 = t1 instanceof Deadline ? ((Deadline) t1).getDate() : ((Event) t1).getFrom();
            LocalDateTime date2 = t2 instanceof Deadline ? ((Deadline) t2).getDate() : ((Event) t2).getFrom();
            return date1.compareTo(date2);
        }
    };
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given tasks.
     *
     * @param tasks The tasks to be added to the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a copy of the list of tasks.
     *
     * @return A copy of the list of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the description of the task at the specified index.
     *
     * @param index The index of the task whose description is to be returned.
     * @return The description of the task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public String getTaskString(int index) throws IndexOutOfBoundsException {
        return tasks.get(index).toString();
    }

    /**
     * Returns the status of the task at the specified index.
     *
     * @param index The index of the task whose status is to be returned.
     * @return The status of the task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public String getTaskStatus(int index) throws IndexOutOfBoundsException {
        return tasks.get(index).getStatusIcon();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Finds tasks that contain the keyword in their description.
     * Returns a TaskList containing the matching tasks.
     *
     * @param keyword Keyword to search for.
     * @return TaskList containing tasks that match the keyword.
     * @throws SpikeException If no tasks match the keyword.
     */
    public TaskList findTask(String keyword) throws SpikeException {
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        if (matchingTasks.getSize() > 0) {
            return matchingTasks;
        } else {
            throw new SpikeException("No matching tasks found");
        }
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void markTaskDone(int index) throws IndexOutOfBoundsException {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void markTaskUndone(int index) throws IndexOutOfBoundsException {
        tasks.get(index).markAsUndone();
    }

    /**
     * Returns a list of user's deadlines and events sorted by date (if applicable).
     *
     * @return A list of user's deadlines and event sorted by date.
     */
    public ArrayList<Task> listTasksByDate() {
        return tasks.stream()
                .filter(t -> t instanceof Deadline || t instanceof Event)
                .sorted(dateComparator)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
